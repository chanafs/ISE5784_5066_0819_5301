/**
 * 
 */
package geometries;
import primitives.Double3;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 */
public class Geometries implements Intersectable{
	private final List<Intersectable> intersectables = null;

    public Geometries() {
        this.intersectables= new LinkedList<>();
    }

    public Geometries(Intersectable... intersectables) {
        this.intersectables = new LinkedList<>();
        add(intersectables);
    }

  public void add(Intersectable... intersectables) {
        for(Intersectable item : intersectables)
        {
            this.intersectables.add(item);
        }
    }

    @Override
    public List<Double3> findIntersections(Ray ray) {
        List<Double3> result = null;
        for(Intersectable item : this.intersectables){
            List<Double3>itemPoints = item.findIntersections(ray);
            if(itemPoints!= null){
                if(result == null){
                    result = new LinkedList<>();
                }
                result.addAll(itemPoints);
            }
        }
        return result;
    }
	
	
	
	
}
