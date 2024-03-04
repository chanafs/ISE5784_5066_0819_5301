package geometries;
import primitives.*; 
import java.util.List;
import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Plane extends Geometry { 

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
    
    /**

    Finds the intersections between the plane and a given ray.

    Overrides the method in the Geometry class.

    @param ray The ray to intersect with the plane.

    @return A list of GeoPoints representing the intersections, or null if there are no intersections.
    */
   @Override
   public List<GeoPoint> findGeoIntersections(Ray ray) {
       Point P0 = ray.getHead();
       Vector v = ray.getDirection();

       Vector n = normal;

       if(pointPlane.equals(P0)) {
           return null;
       }
       Vector P0_Q0 = pointPlane.subtract(P0);

       // numerator
       double nP0Q0 = alignZero(n.dotProduct(P0_Q0));

       if(isZero(nP0Q0)){
           return null;
       }

       //denominator
       double nv = alignZero(n.dotProduct(v));

       // if ray is lying in the plane axis
       if(isZero(nv)){
           return null;
       }

       double t = alignZero(nP0Q0 / nv);

       if (t <= 0){
           return null;
       }

       Point point = ray.getPoint(t);
       GeoPoint geoPoint = new GeoPoint(this, point);
       return List.of(geoPoint);

   }

   /**

    Helper method for finding the intersections between the plane and a given ray.
    Overrides the method in the Geometry class.
    @param ray The ray to intersect with the plane.
    @return A list of GeoPoints representing the intersections, or null if there are no intersections.
    */
   @Override
   protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
	   Point p0 = ray.getHead();
	   Vector v = ray.getDirection();
	   if (pointPlane.equals(p0))
			return null;
	   double nv = normal.dotProduct(v);
		// Ray is parallel to the plane
	   if (isZero(nv))
			return null;
	   double t = (pointPlane.subtract(p0)).dotProduct(normal) / nv;
		// Checking if intersection is behind the start of the ray
	   return (alignZero(t) <= 0) ? null : List.of(new GeoPoint(this, ray.getPoint(t)));
	}
}
