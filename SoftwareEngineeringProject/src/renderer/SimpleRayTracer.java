package renderer;

import primitives.*;
import java.util.List;
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
    
    //joyce: 
    private Color calcColor(GeoPoint intersection, Ray ray) {
    	return scene.ambientLight.getIntensity()
    	.add(calcLocalEffects(intersection, ray));
    	}
    private Color calcColor(GeoPoint gp) {
    	return scene.ambientLight.getIntensity()
    	.add(gp.geometry.getEmission());
    	}


    
    /**
     * Calculates the color of the point using the ambient
     * light intensity of the scene
     * @param p point that's color is being calculated
     * @param gp point that's color is being calculated
     * @return the Color of that point
     
    public Color calcColor(Point p) {
        return scene.ambientlight.getIntensity();
    }
    */
    
    /**
     Calculates the local effects (diffuse and specular) of a given geometric point on a ray.
     @param gp The geometric point at which to calculate the local effects.
     @param ray The ray being traced.
     @return The color resulting from the local effects.
     */
    //joyce: 
    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
    	Color color = gp.geometry.getEmission();
    	Vector n = gp.geometry.getNormal(gp.point);
    	Vector v = ray.getDirection();
    	double nv = alignZero(n.dotProduct(v));
    	if (nv == 0) return color;
    	Material material = gp.geometry.getMaterial();
  
    	for (LightSource lightSource : scene.lights) {
    		Vector l = lightSource.getL(gp.point); 
    		double nl = alignZero(n.dotProduct(l);
    		if ((nl * nv > 0) && unshaded(gp, l)) { // sign(nl) == sign(nv)
    		Color iL = lightSource.getIntensity(gp.point);
    		color = color.add(
    		iL.scale(calcDiffusive(mat, nl)
    		.add(calcSpecular(mat, n, l, nl, v)));
    		}
    	}
    		return color;

  }
    
    //from joyce 
    private boolean unshaded(GeoPoint gp, Vector l) {
    	Vector lightDirection = l.scale(-1); // from point to light source
    	Ray ray = new Ray(gp.point, lightDirection);
    	List<GeoPoint> intersections = scene.geometries.findIntersections(ray);
    	return intersections is empty;
    	}
    /*
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
   

    /*
    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
        Vector v = ray.getDirection();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = Util.alignZero(n.dotProduct(v));
        int nShininess = gp.geometry.getMaterial().nShininess;
        Double3 kd = gp.geometry.getMaterial().kD;
        Double3 ks = gp.geometry.getMaterial().kS;

        Color color = new Color(0, 0, 0);
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = Util.alignZero(n.dotProduct(l));
            if(nl*nv > 0 ) {
                Color intensity = lightSource.getIntensity(gp.point);
                color = color.add(calcDiffusive(kd,l,n,intensity), calcSpecular(ks,l,n,v,nShininess,intensity));
            }
           
        }
        return color;
    }**/
    
        /**


    