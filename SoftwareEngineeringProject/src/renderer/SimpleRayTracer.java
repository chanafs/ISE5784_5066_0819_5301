package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

public class SimpleRayTracer extends RayTraceBase {
    public SimpleRayTracer(Scene s) {
        super(s);
    }

    @Override
    public Color traceRay(Ray ray) {
        var intersections = scene.geometries.findGeoIntersections(ray);
        return intersections == null
            ? scene.background
            : calcColor(ray.findClosestGeoPoint(intersections));
    }

    private Color calcColor(GeoPoint gp) {
        return gp == null
            ? scene.background
            : scene.ambientlight.getIntensity().add(gp.geometry.getEmission());
    }
}