
package geometries;
import primitives.*;
import java.util.List;

/*interface for finding intersection points
*/
public interface Intersectable {
     List<Point> findIntersections(Ray ray);
}