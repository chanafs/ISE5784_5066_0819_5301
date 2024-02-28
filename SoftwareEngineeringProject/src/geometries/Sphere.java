package geometries;
import java.util.List;
import static primitives.Util.alignZero;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
/**

The Sphere class represents a sphere in three-dimensional space.
It extends the RadialGeometry abstract class and implements the Geometry interface.
*/

public class Sphere extends RadialGeometry{
	private Point center; 
	  /*
     * Constructs a Sphere object with the specified radius and center point.
     *
     * @param r: the radius of the sphere
     * @param c: the center point of the sphere
     */
	public Sphere(double r, Point c) {
		super(r); //sets radius by sending it to constructor of RadialGeometry
		this.setCenter(c);
	}

	public Vector getNormal(Point p) {
		return (p.subtract(center)).normalize();
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}
	  /**

    Finds the intersections between the sphere and a given ray.

    Overrides the method in the Geometry class.

    @param ray The ray to intersect with the sphere.

    @return A list of GeoPoints representing the intersections, or null if there are no intersections.
    */
	
	//didnt update this help fn!!!!!
   @Override
   public List<GeoPoint> findGeoIntersections(Ray ray) {
       Point P0 = ray.getHead();
       Vector v = ray.getDirection();

       if (P0.equals(center)) {
           Point point = center.add(v.scale(radius));
           return List.of(new GeoPoint(this, point));
       }

       Vector U = center.subtract(P0);

       double tm = alignZero(v.dotProduct(U));
       double d = alignZero(Math.sqrt(U.lengthSquared() - tm * tm));

       // no intersections : the ray direction is above the sphere
       if (d >= radius) {
           return null;
       }

       double th = alignZero(Math.sqrt(radius * radius - d * d));
       double t1 = alignZero(tm - th);
       double t2 = alignZero(tm + th);

       if (t1 > 0 && t2 > 0) {
           Point P1 = ray.getPoint(t1);
           Point P2 = ray.getPoint(t2);
           return List.of(new GeoPoint(this, P1), new GeoPoint(this, P2));
       }
       if (t1 > 0) {
           Point P1 = ray.getPoint(t1);
           return List.of(new GeoPoint(this, P1));
       }
       if (t2 > 0) {
           Point P2 = ray.getPoint(t2);
           return List.of(new GeoPoint(this, P2));
       }
       return null;
   }

   /**

    Helper method for finding the intersections between the sphere and a given ray.
    Overrides the method in the Geometry class.
    @param ray The ray to intersect with the sphere.
    @return A list of GeoPoints representing the intersections, or null if there are no intersections.
    */
   
   
   @Override
   protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
       List<Point> intersections = this.findIntersections(ray);
       if (intersections == null) {
           return null;
       }
       Point point = intersections.get(0);
       Point point2;
       if (intersections.size() == 2) {
           point2 = intersections.get(1);
           return List.of(new GeoPoint(this, point), new GeoPoint(this, point2));
       }
       return List.of(new GeoPoint(this, point));
   }
   /*
   @Override
	public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
		Point p0 = ray.getHead();
		Vector dir = ray.getDirection();
		// Deals with case where ray starts from the center of the sphere
		if (p0.equals(center))
			return List.of(new GeoPoint(this, ray.getPoint(this.radius)));
		// Finding the hypotenuse, base and perpendicular of the triangle formed by
		// ray's starting point, the center of the sphere and the intersection point of
		// the ray and the perpendicular line crosing the sphere's center.
		Vector hypotenuse = this.center.subtract(p0);
		double base = dir.dotProduct(hypotenuse);
		double perpendicular = hypotenuse.lengthSquared() - base * base;
		double insideSquared = this.radiusSquared - perpendicular;

		// Dealing with a case in which the ray is perpendicular to the sphere at the
		// intersection point, or passes outside the Sphere.
		if (alignZero(insideSquared) <= 0)
			return null;

		// Returning intersection points, ensuring that only those intersected by the
		// ray are returned.
		double inside = Math.sqrt(insideSquared);
		double t2 = base + inside;

		if (alignZero(t2) <= 0)
			return null;
		double t1 = base - inside;
		if (alignZero(t1) > 0)
			return List.of(new GeoPoint(this, ray.getPoint(t1)), new GeoPoint(this, ray.getPoint(t2)));
		return List.of(new GeoPoint(this, ray.getPoint(t2)));
	}*/
}
   
