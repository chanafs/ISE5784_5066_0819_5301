package geometries;
import primitives.*;
import java.util.List;

<<<<<<< Upstream, based on origin/main
/** interface for finding intersection points
=======
/*
 * Intersectable is an interface for finding intersection points
>>>>>>> 41e167c latest changes 6/2/24
*/
public interface Intersectable {
     List<Point> findIntersections(Ray ray);
}