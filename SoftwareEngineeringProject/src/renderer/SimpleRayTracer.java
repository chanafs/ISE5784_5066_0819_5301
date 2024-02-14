package renderer;

import primitives.*;
import primitives.Double3;
import primitives.Ray;
import scene.Scene;
import geometries.Intersectable.GeoPoint;
import lighting.LightSource;

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
    /**
     * Calculates the color of the point using the ambient
     * light intensity of the scene
     * @param p point that's color is being calculated
     * @param gp point that's color is being calculated
     * @return the Color of that point
     */
    public Color calcColor(Point p) {
        return scene.ambientlight.getIntensity();
    }
    public Color calcColor(GeoPoint gp, Ray ray) {
        //gp.geometry.getIntensity(gp.point);
        return scene.ambientlight.getIntensity().add(calcLocalEffects(gp, ray));
}
    
    
    /**
     Calculates the local effects (diffuse and specular) of a given geometric point on a ray.
     @param gp The geometric point at which to calculate the local effects.
     @param ray The ray being traced.
     @return The color resulting from the local effects.
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
        Vector v = ray.getDirection();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = Util.alignZero(n.dotProduct(v));
        int nShininess = gp.geometry.getMaterial().nShininess;
        Double3 kd = gp.geometry.getMaterial().kD;
        Double3 ks = gp.geometry.getMaterial().kS;

        Color color = new Color(0,0,0);
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = Util.alignZero(n.dotProduct(l));
            if(nl*nv > 0 ) {
                Color intensity = lightSource.getIntensity(gp.point);
                color = color.add(calcDiffusive(kd,l,n,intensity), calcSpecular(ks,l,n,v,nShininess,intensity));
            }
           
        }
        return color;
    }
        /**
        Calculates the specular reflection component for a given material.
        @param ks The specular reflection coefficient of the material.
        @param l The direction of the light source.
        @param n The surface normal at the point of reflection.
        @param v The direction from the point of reflection towards the viewer.
        @param nShininess The shininess factor of the material.
        @param lightIntensity The intensity of the light source.
        @return The color resulting from the specular reflection.
        */
       private Color calcSpecular(Double3 ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity){
           Vector r = l.subtract(n.scale(2*(l.dotProduct(n))));
           double vrMinus = Math.max(0, v.scale(-1).dotProduct(r));
           double vrn =Math.pow(vrMinus,nShininess);
           return lightIntensity.scale(ks.scale(vrn));
       }
       /**
       Calculates the diffuse reflection component for a given material.
       @param kd The diffuse reflection coefficient of the material.
       @param l The direction of the light source.
       @param n The surface normal at the point of reflection.
       @param intensity The intensity of the light source.
       @return The color resulting from the diffuse reflection.
       */
      private Color calcDiffusive(Double3 kd, Vector l, Vector n, Color intensity){
          double ln = Math.abs(l.dotProduct(n));
          return intensity.scale(kd.scale(ln));
      
  }
       
    }

    