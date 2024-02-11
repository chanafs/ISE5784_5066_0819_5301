/**
 * 
 */
package renderer;
import primitives.Ray;
import scene.Scene;

/**
 * 
 */
public abstract class RayTraceBase {
protected Scene scene;

RayTraceBase(Scene s) {
    scene = s;
}
/**

The traceRay method is an abstract method that must be implemented by subclasses of
RayTracerBase to perform the actual ray tracing.
@param ray The Ray object representing the ray being traced.
@return The Color object representing the color of the traced ray.
*/
public abstract primitives.Color traceRay(Ray ray);
}