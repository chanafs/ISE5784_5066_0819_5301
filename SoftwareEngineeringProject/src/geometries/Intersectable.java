package geometries;
import primitives.*;
import java.util.List; 
/*
 * Intersectable is an interface for finding intersection points
*/
public interface Intersectable {
     List<Point> findIntersections(Ray ray);
}