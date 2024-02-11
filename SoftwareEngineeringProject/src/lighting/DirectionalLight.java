/**
 * 
 */
package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * 
 *  Represents a directional light source in the scene.
 */
public class DirectionalLight extends Light implements LightSource {

	/**
	 * @param intensity
	 */
	
	private Vector direction;
	
    /**

    Constructs a new DirectionalLight with the specified color and direction.
    @param color The color of the light.
    @param direction The direction of the light.
    */
	public DirectionalLight(Color intensity) {
		super(intensity);
		// TODO Auto-generated constructor stub
	}

    /**

    Gets the intensity of the directional light at the specified point.
    @param p The point at which to calculate the intensity.
    @return The intensity of the light.
    */
	@Override
	public Color getIntensity(Point p) {
		return getIntensity();
	}
	
    /**
    Gets the direction of the light at the specified point.
    @param p The point at which to calculate the direction.
    @return The direction vector of the light.
    */
	@Override
	public Vector getL(Point p) {
        return direction;
	}
	
    /**
     * Gets the distance between a directional light and a given point (of the object)
     * @param point The point to which we're finding the distance to.
     * @return The distance which is always POSITIVE_INFINITY
     */
    public double getDistance(Point point) {
        return Double.POSITIVE_INFINITY;
    }

}
