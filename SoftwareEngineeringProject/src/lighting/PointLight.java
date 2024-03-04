package lighting;

import primitives.Color;
import primitives.Double3;
import primitives.Point;
import primitives.Vector;

/**

 Represents a point light source in the scene.
 */
public class PointLight extends Light implements LightSource {
    private final Point position;
    //private Double3 kC = Double3.ONE;
    //private Double3 kL = Double3.ZERO;
    //private Double3 kQ = Double3.ZERO;
    private double kC = 1, kL = 0, kQ = 0;

    /**

     Constructs a new PointLight with the specified color and position.
     @param color The color of the light.
     @param position The position of the light.
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }
    /**
	 * Creates a point light.
	 * 
	 * @param intensity light's intensity
	 * @param position  light source's location
	 * @param kC        constant attenuation factor
	 * @param kL        linear attenuation factor
	 * @param kQ        quadratic attenuation factor
	 */
	public PointLight(Color intensity, Point position, double k1, double k2, double k3) {
		super(intensity);
		this.position = position;
		this.kC = k1;
		this.kL = k2;
		this.kQ = k3;
	}
    /**

     Gets the intensity of the point light at the specified point.
     @param p The point at which to calculate the intensity.
     @return The intensity of the light.
     */
	@Override
	public Color getIntensity(Point p) {
		// Calculates lights intensity at a point given it's distance from the point
		// light
		double dSquare = position.distanceSquared(p);
		return intensity.reduce(kC + kL * Math.sqrt(dSquare) + kQ * dSquare);
	}
    /**

     Gets the direction vector from the point light to the specified point.
     @param p The point at which to calculate the direction.
     @return The direction vector of the light.
     */
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }

    /**
     * Gets the distance between a point light and a given point (of the object)
     * @param point The point to which we're finding the distance to.
     * @return The distance between the point light and the point on the object
     */
    public double getDistance(Point point) {
        return point.distance(position);
    }
    /**
	 * Sets the constant attenuation factor.
	 * 
	 * @param k the constant attenuation factor.
	 */
	public PointLight setKc(double k) {
		this.kC = k;
		return this;
	}
	/**
	 * Sets the linear attenuation factor.
	 * 
	 * @param k the linear attenuation factor.
	 */
	public PointLight setKl(double k) {
		this.kL = k;
		return this;
	}
	/**
	 * Sets the quadratic attenuation factor.
	 * 
	 * @param k the quadratic attenuation factor.
	 */
	public PointLight setKq(double k) {
		this.kQ= k;
		return this;
	}
	
}
    
