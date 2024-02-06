package geometries;
import primitives.*; 
import java.util.List;
import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Plane implements Geometry { 

	private Point pointPlane; 
	private Vector normal; 
	
	/*
     * Constructs a plane from a point on the plane and a normal vector to the plane
     * The normal vector is normalized.
     * @param q: Point on the plane.
     * @param normal: Normal vector to the plane.
     */
	public Plane(Point q, Vector normal) {
		normal.normalize();
		this.pointPlane = q;
		this.normal = normal;
	}
	 /*
     * Constructs a plane from three points on the plane
     * @param a: First point on the plane
     * @param b: Second point on the plane
     * @param c: Third point on the plane
     */
	public Plane(Point a, Point b, Point c) {
		 Vector U = b.subtract(a);
	     Vector V = c.subtract(a);
	     Vector N = U.crossProduct(V);
	     N.normalize();
	     this.normal = N;
	     this.pointPlane = a;
	}
	/*
	 * getNormal returns the normal of the plane 
	 */
	public Vector getNormal() {
		return this.normal; 
	}
	public Vector getNormal(Point p) {
		return this.normal; 
	}
	
    /*
    * Returns a string representation of the plane
    */
    @Override
    public String toString() {
        return "Plane{" + "pointPlane=" + pointPlane + ", normal=" + normal + '}';        
    }
    
    /*
     * Finds the intersections between the plane and a given ray.
     * @param ray: The ray to intersect with the plane.
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        Point p0 = ray.getHead();    // Starting point of the ray
        Vector v = ray.getDirection();    // Direction vector of the ray
        Vector n = normal;    // Normal vector of the plane
        
        // Check if the ray starts from a point on the plane; if so, no intersection
        if (pointPlane.equals(p0)) {
            return null;
        }

        // Calculate the dot product of the plane's normal and the ray's direction
        double nv = alignZero(n.dotProduct(v));

        // Check if the ray is parallel or lying in the plane's axis; if so, no intersection
        if (isZero(nv)) {
            return null;
        }

        // Calculate vector Q0P0 from a point on the plane to the starting point of the ray
        Vector Q0P0 = pointPlane.subtract(p0);

        // Calculate the numerator for the intersection parameter
        double nQMinusP0 = alignZero(n.dotProduct(Q0P0));

        // Check if the ray is parallel to the plane; if so, no intersection
        if (isZero(nQMinusP0)) {
            return null;
        }

        // Calculate the intersection parameter 't'
        double t = alignZero(nQMinusP0 / nv);

        // Check if 't' is non-positive; if so, no intersection
        if (t <= 0) {
            return null;
        }

        // Return a list containing the intersection point on the ray
        return List.of(ray.getPoint(t));
    }
	}
