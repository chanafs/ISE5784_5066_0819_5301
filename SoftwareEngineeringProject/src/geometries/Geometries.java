package geometries;
import primitives.Point;
import primitives.Ray;
import java.util.LinkedList;
import java.util.List;

/*
*Geometries represents a collection of intersectable objects.
*/
public class Geometries extends Intersectable{
	private List<Intersectable> intersectables = null;
	/*
	* constructor 
	*/
    public Geometries() {
        this.intersectables= new LinkedList<>();
    }
    /*
	* constructor
	* @param intersectables: array of Intersectable objects to be added to intersectables list 
	*/
    public Geometries(Intersectable... intersectables) {
        this.intersectables = new LinkedList<>(); 
        add(intersectables);
    }
    /*
	* constructor
	* @param intersectables: array of Intersectable objects to be added to intersectables list 
	*/
    public void add(Intersectable... intersectables) {
        for(Intersectable item : intersectables)
        {
            this.intersectables.add(item);
        }
    }
    
    
    
    /*
	* constructor
	* @param ray: find intersection between the given ray and the geometries 
	
    @Override
    public List<Point> findIntersections(Ray ray) {
    	//stores the intersection Points in result 
    	List<Point> result = null;
        for(Intersectable item : this.intersectables){
            List<Point>itemPoints = item.findIntersections(ray);
            //if there are intersections with the current Intersectable object
            if(itemPoints!= null){
                if(result == null){
                    result = new LinkedList<>();
                }
                //add intersections to result 
                result.addAll(itemPoints);
            }
        }
        return result;
    } */
    /**

    Finds the intersections between the Geometries and a given ray.
    Overrides the method in the Geometry class.
    @param ray The ray to intersect with the Geometries.
    @return A list of GeoPoints representing the intersections, or null if there are no intersections.
    */
   @Override
   public List<GeoPoint> findGeoIntersections(Ray ray) {
       if (intersectables.isEmpty()) {
           return null;
       }
       List<GeoPoint> list = null;
       for(Intersectable inter : intersectables) {
           List<GeoPoint> smallList = inter.findGeoIntersections(ray);
           if (smallList != null) {
               if (list == null)
                   list = new LinkedList<>();
               // intersectables.add(inter);
               list.addAll(smallList);
           }
       }
       if(list != null)
           return list;
       else return null;
   }

   /**

    Helper method for finding the intersections between the geometries bodies in the scene and a given ray.
    Overrides the method in the Geometry class.
    @param ray The ray to intersect with the geometric bodies in the scene.
    @return A list of GeoPoints representing the intersections, or null if there are no intersections.
    */
   @Override
   protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
       List<GeoPoint> intersections = null;
       for(Intersectable intersectable : intersectables) {
           List<GeoPoint> geometryIntersections = intersectable.findGeoIntersections(ray);
           if (!geometryIntersections.isEmpty())
               for (GeoPoint geo : geometryIntersections) {
                   intersections.add(geo);
               }
       }
       return intersections;
   }
}
