package geometries;
import primitives.Ray;
import java.util.LinkedList;
import java.util.List;

public class Geometries extends Intersectable{
	private List<Intersectable> intersectables = new LinkedList<>();
	
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
        //this.intersectables = new LinkedList<>(); 
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
	   List<GeoPoint> result = null;
       //List<GeoPoint> intersections = null;
       for(Intersectable intersectable : intersectables) {
    	   var toAdd =intersectable.findGeoIntersections(ray);
    	   if (toAdd != null) {
    		   if (result == null)
					result = new LinkedList<>();
    		   result.addAll(toAdd);
    	   }
       }
          return result; 
      }
   
} 
   