package geometries;
import primitives.*;
import java.util.List; 
//import static geometries.Intersectable.GeoPoint; 
/*
 * Intersectable is an abstract class used for finding intersection points
*/
public abstract class Intersectable {
	
	
	  /**
     * Finds the intersection points between the specified ray and this object.
     *
     * @param ray the ray to intersect with this object
     * @return a list of intersection points between the ray and this object,
     *         or an empty list if there are no intersections
     */
     public  List<Point> findIntersections(Ray ray){
    	 var geoList = findGeoIntersections(ray);
         return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
     }
     
     /**
      * Finds intersections between the intersectable object and a given ray, returning GeoPoints.
      * This method follows the Non-Virtual Interface (NVI) design pattern and delegates to the
      * findGeoIntersectionsHelper method.
      *
      * @param ray The ray to find intersections with.
      * @return A list of GeoPoint objects representing the intersection points.
      */
     public abstract List<GeoPoint> findGeoIntersections(Ray ray); //{
         //return findGeoIntersectionsHelper(ray);
   //  }

     /**
      * Protected abstract method for subclasses to implement, finding intersections with GeoPoints.
      * Subclasses should provide their specific implementation of intersection logic.
      *
      * @param ray The ray to find intersections with.
      * @return A list of GeoPoint objects representing the intersection points.
      */
     protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);

     
     
     public static class GeoPoint {
    	 /** The geometric entity associated with this GeoPoint. */
    	 public Geometry geometry;
    	 
    	 /** The point in three-dimensional space associated with this GeoPoint. */
    	 public Point point;
    	 /**
    	     * Constructs a GeoPoint with the given Geometry and Point.
    	     *
    	     * @param geometry The geometric entity associated with this GeoPoint.
    	     * @param point The point in three-dimensional space associated with this GeoPoint.
    	     */
    	    public GeoPoint(Geometry geometry, Point point) {
    	        this.geometry = geometry;
    	        this.point = point;
    	    }
    	    
    	    /**
    	     * Checks if this GeoPoint is equal to another object.
    	     *
    	     * @param obj The object to compare with this GeoPoint.
    	     * @return true if the objects are equal, false otherwise.
    	     */
    	    @Override
    	    public boolean equals(Object obj) {
    	        if (this == obj) return true;
    	        /*if (obj == null || getClass() != obj.getClass()) return false;
    	        GeoPoint other = (GeoPoint) obj;
    	        return geometry.equals(other.geometry) && point.equals(other.point);*/
    	        return obj instanceof GeoPoint other && (this.geometry == other.geometry) && (this.point.equals(other.point));
    	    }
    	    
    	    /**
    	     * Returns a string representation of this GeoPoint.
    	     *
    	     * @return A string representation of this GeoPoint.
    	     */
    	    @Override
    	    public String toString() {
    	        return "GeoPoint{" +
    	               "geometry=" + geometry +
    	               ", point=" + point +
    	               '}';
    	    }

    	}
     
}