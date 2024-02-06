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
    
    /*
     * Finds the intersections between the triangle and ray.
     */
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
    }
}