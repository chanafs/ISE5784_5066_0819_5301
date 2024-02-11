package geometries;
import java.util.List;
import primitives.Vector; 
import primitives.Point;
import primitives.Ray;

public class Triangle extends Polygon{
	
    /*
     * Constructs a Triangle object with the given Points 
     * @param p1 vertex of the triangle
     * @param p2 vertex of the triangle
     * @param p3 vertex of the triangle
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3); //sends to the constructor of Polygon 
    }
    /**

    Finds the intersections between the triangle and a given ray.

    Overrides the method in the Geometry class.

    @param ray The ray to intersect with the triangle.

    @return A list of GeoPoints representing the intersections, or null if there are no intersections.
    */
   @Override
   public List<GeoPoint> findGeoIntersections(Ray ray) {
       if (this.plane.findGeoIntersections(ray) == null) {
           return null;
       }

       Point p0 = ray.getHead();
       Vector v = ray.getDirection();

       // get the vertices of the triangle
       Point p1 = vertices.get(0);
       Point p2 = vertices.get(1);
       Point p3 = vertices.get(2);

       Vector v1 = p1.subtract(p0);
       Vector v2 = p2.subtract(p0);
       Vector v3 = p3.subtract(p0);

       Vector n1 = v1.crossProduct(v2).normalize();
       Vector n2 = v2.crossProduct(v3).normalize();
       Vector n3 = v3.crossProduct(v1).normalize();

       // check if the ray intersects the triangle using the dot product of the ray's direction vector and the
       // normal vectors of the three edges of the triangle
       if (v.dotProduct(n1) > 0 && v.dotProduct(n2) > 0 && v.dotProduct(n3) > 0) {
           Plane plane = new Plane(p1, p2, p3);
           List<Point> list = plane.findIntersections(ray);
           return List.of(new GeoPoint(this, list.get(0)));
       }
       if (v.dotProduct(n1) < 0 && v.dotProduct(n2) < 0 && v.dotProduct(n3) < 0) {
           Plane plane = new Plane(p1, p2, p3);
           List<Point> list = plane.findIntersections(ray);
           return List.of(new GeoPoint(this, list.get(0)));
       }
       else
           // if there is no intersection, return null
           return null;
   }

   /**

    Helper method for finding the intersections between the triangle and a given ray.
    Overrides the method in the Geometry class.
    @param ray The ray to intersect with the triangle.
    @return A list of GeoPoints representing the intersections, or null if there are no intersections.
    */
   @Override
   protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
       List<Point> intersections = this.findIntersections(ray);
       if (intersections == null) {
           return null;
       }
       Point point = intersections.get(0);
       return List.of(new GeoPoint(this, point));
   }
    /*
     * Finds the intersections between the triangle and ray.
     
    @Override 
    public List<Point> findIntersections(Ray ray) {
        // Find intersections with the plane containing the triangle
        List<Point> intersectionList = plane.findIntersections(ray);

        // If there are no intersections with the plane, return null
        if (intersectionList == null) {
            return null;
        }

        // Calculate vectors and normals for each triangle vertex
        Vector v1 = ray.getHead().subtract(vertices.get(0));
        Vector v2 = ray.getHead().subtract(vertices.get(1));
        Vector v3 = ray.getHead().subtract(vertices.get(2));
        Vector n1 = (vertices.get(1).subtract(vertices.get(0))).crossProduct(v1).normalize();
        Vector n2 = (vertices.get(2).subtract(vertices.get(1))).crossProduct(v2).normalize();
        Vector n3 = (vertices.get(0).subtract(vertices.get(2))).crossProduct(v3).normalize();

        // Calculate dot products between ray direction and vertex normals
        double number1 = ray.getDirection().dotProduct(n1);
        double number2 = ray.getDirection().dotProduct(n2);
        double number3 = ray.getDirection().dotProduct(n3);

        // Check if the ray direction is within the half-space defined by the triangle
        if (number1 >= 0 && number2 >= 0 && number3 >= 0) {
            return intersectionList;
        }

        // Check if the ray direction is within the other half-space defined by the triangle
        if (number1 <= 0 && number2 <= 0 && number3 <= 0) {
            return intersectionList;
        }

        // If the ray does not intersect with the triangle, return null
        return null;
    } */
}