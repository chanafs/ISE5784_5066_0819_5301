package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Represents a spotlight with a specific direction, intensity, and position,
 * along with attenuation coefficients. This light source simulates a real-world
 * spotlight by focusing light in a specific direction with a defined position.
 */
public class SpotLight extends PointLight implements LightSource {

    private  Vector direction;
    private double angle =1;
    /**
	 * Creates a spot light.
	 * 
	 * @param intensity light's intensity
	 * @param position  light source's location
	 * @param kC        constant attenuation factor
	 * @param kL        linear attenuation factor
	 * @param kQ        quadratic attenuation factor
	 * @param direction direction in which the light is pointing
	 */
	public SpotLight(Color intensity, Point position, double Kc, double Kl, double Kq, Vector direction) {
		super(intensity, position, Kc, Kl, Kq);
		this.direction = direction.normalize();
	}
	/**
	 * Default constructor for a spot light.
	 * 
	 * @param intensity light's intensity
	 * @param position  point light's position
	 * @param direction direction in which the light is pointing
	 */
	public SpotLight(Color intensity, Point position, Vector direction) {
		super(intensity, position);
		this.direction = direction.normalize();
	}
    /**
     * Sets the direction vector of the spotlight and returns the SpotLight object itself,
     * enabling fluent configuration. This method allows for setting the direction of the
     * spotlight's beam, which is essential for its focused lighting effect.
     *
     * @param direction the direction vector of the spotlight
     * @return the SpotLight object, enabling method chaining
     */
    public SpotLight setDirection(Vector d) {
        this.direction = d;
        return this;
    }
    
    /**

    Gets the intensity of the spotlight at the specified point.
    @param p The point at which to calculate the intensity.
    @return The intensity of the light.
    */
   public Color getIntensity(Point p) {
	   return super.getIntensity(p).
			   scale(Math.pow(Math.max(0, direction.dotProduct(super.getL(p))), angle));
   }
    public SpotLight setNarrowBeam(double n) {
       this.angle = n;
       return this;
   }
    
}


