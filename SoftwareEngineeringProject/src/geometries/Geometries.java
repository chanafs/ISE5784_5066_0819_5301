package geometries;
import primitives.Point;
import primitives.Ray;
import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable{
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
	* @param ray 
	*/
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
    }
	
	
	
	
}
