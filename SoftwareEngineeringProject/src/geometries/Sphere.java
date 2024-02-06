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
	/*
    *Finds the intersections between the sphere and ray
    *Overrides the method in the Geometry class
    *@param ray The ray to intersect with the sphere
    */
	@Override
	 public List<Point> findIntersections(Ray ray) {
	        Point P0 = ray.getHead();
	        Vector v = ray.getDirection();

	        // Check if the ray starts from the center of the sphere; if so, returns the sphere's surface point
	        if (P0.equals(center)) {
	            return List.of(center.add(v.scale(radius)));
	        }

	        // Vector from the starting point of the ray to the center of the sphere
	        Vector U = center.subtract(P0);

	        // Calculate the projection of U onto the ray's direction
	        double tm = alignZero(v.dotProduct(U));

	        // Calculate the distance between the ray and the sphere's center
	        double d = alignZero(Math.sqrt(U.lengthSquared() - tm * tm));

	        // Check for no intersection (ray misses the sphere)
	        if (d >= radius) {
	            return null;
	        }

	        // Calculate the half-chord length (distance from the center to the intersection points)
	        double th = alignZero(Math.sqrt(radius * radius - d * d));

	        // Calculate the two potential intersection parameters
	        double t1 = alignZero(tm - th);
	        double t2 = alignZero(tm + th);

	        // Check for valid positive intersection parameters and return the intersection points
	        if (t1 > 0 && t2 > 0) {
	            Point P1 = ray.getPoint(t1);
	            Point P2 = ray.getPoint(t2);
	            return List.of(P1, P2);
	        }

	        // Check for a single valid intersection parameter and return the intersection point
	        if (t1 > 0) {
	            Point P1 = ray.getPoint(t1);
	            return List.of(P1);
	        }

	        // Check for a single valid intersection parameter and return the intersection point
	        if (t2 > 0) {
	            Point P2 = ray.getPoint(t2);
	            return List.of(P2);
	        }

	        // No valid positive intersection parameter found; return null (no intersection)
	        return null;
	    }
	
}
