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
	/**
	 * constructor 
	 * @param scene Scene
	 */
	SimpleRayTracer(Scene s) {
		super(s);
	}
    @Override
    /*
     * • implement the ray tracing method in the SimpleRayTracer class:
o method will search intersections between the ray and the scene 3D model
o if there are no intersections – return the scene’s background
מיני-פרויקט במבוא להנדסת תוכנה – תשפ"א
o otherwise, search the closest point to the head of ray and return the color of this point
using the calcColor method (new method).
     * */
    public primitives.Color traceRay(Ray ray) {
        List<Point> intersections = scene.geometries.findIntersections(ray);
        if (intersections != null) {
            Point closestPoint = ray.findClosestPoint(intersections);
            return calcColor(closestPoint);
        }
        //ray did not intersect any geometrical object
        return scene.background;
    }
/*
 * private calcColor method that receives a Point as parameter and returns it’s Color
 * */
    private Color calcColor(Point point) {
        return scene.ambientlight.getIntensity();
    }
}

