/**
 * 
 */
package renderer;

//import java.awt.Color;
import java.util.List;
import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

/**
 * 
 */
public class SimpleRayTracer extends RayTraceBase {
	SimpleRayTracer(Scene s) {
		super(s);
	}
	

    /*
     * Implements the ray tracing method in the SimpleRayTracer class:
     * Searches for intersections between the given ray and the 3D model in the scene.
     * If no intersections are found, returns the background color of the scene.
     * If intersections are found, determines the closest intersection point to the ray's head
     * and returns the color of this point using the calcColor method.
     *
     * @param ray: The ray to trace through the scene.
     * @return The color determined by the closest intersection point or the scene's background color.
     */
    @Override
    public primitives.Color traceRay(Ray ray) {
        // Find intersections between the ray and the 3D model in the scene
        List<Point> intersections = scene.geometries.findIntersections(ray);

        // If intersections are found
        if (intersections != null) {
            // Find the closest intersection point to the ray's head
            Point closestPoint = ray.findClosestPoint(intersections);
            
            // Calculate and return the color of the closest intersection point
            return calcColor(closestPoint);
        }

        // If no intersections are found, return the background color of the scene
        return scene.background;
    }
    /*
     * Private method to calculate the color of a given point in the scene.
     *
     * @param point The 3D point for which to calculate the color.
     * @return The color of the specified point.
     */
    private Color calcColor(Point point) {
        return scene.ambientlight.getIntensity();
    }
}
