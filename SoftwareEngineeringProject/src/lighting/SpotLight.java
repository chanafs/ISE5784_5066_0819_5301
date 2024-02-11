package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Represents a spotlight with a specific direction, intensity, and position,
 * along with attenuation coefficients. This light source simulates a real-world
 * spotlight by focusing light in a specific direction with a defined position.
 */
public class SpotLight extends PointLight {

    private final Vector direction;

    /**
     * Constructs a SpotLight with specified intensity, position, and direction.
     * This constructor initializes a spotlight with all the required properties.
     *
     * @param intensity the color intensity of the light
     * @param position  the position of the light source in the scene
     * @param direction the direction vector of the spotlight
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction;
    }

    /**
     * Sets the constant attenuation coefficient and returns the SpotLight object itself,
     * allowing for method chaining. This method overrides the parent's setKc method
     * to maintain the fluent interface pattern specific to SpotLight.
     *
     * @param kC the constant attenuation coefficient
     * @return the SpotLight object
     */
    @Override
    public SpotLight setKc(double kC) {
        super.setKc(kC);
        return this;
    }

    /**
     * Sets the linear attenuation coefficient and returns the SpotLight object itself,
     * allowing for method chaining. This method overrides the parent's setKl method
     * to maintain the fluent interface pattern specific to SpotLight.
     *
     * @param kL the linear attenuation coefficient
     * @return the SpotLight object
     */
    @Override
    public SpotLight setKl(double kL) {
        super.setKl(kL);
        return this;
    }

    /**
     * Sets the quadratic attenuation coefficient and returns the SpotLight object itself,
     * allowing for method chaining. This method overrides the parent's setKq method
     * to maintain the fluent interface pattern specific to SpotLight.
     *
     * @param kQ the quadratic attenuation coefficient
     * @return the SpotLight object
     */
    @Override
    public SpotLight setKq(double kQ) {
        super.setKq(kQ);
        return this;
    }

    /**
     * Sets the direction vector of the spotlight and returns the SpotLight object itself,
     * enabling fluent configuration. This method allows for setting the direction of the
     * spotlight's beam, which is essential for its focused lighting effect.
     *
     * @param direction the direction vector of the spotlight
     * @return the SpotLight object, enabling method chaining
     */
    public SpotLight setDirection(Vector direction) {
        this.direction = direction;
        return this;
    }
    
    /**

    Gets the intensity of the spotlight at the specified point.
    @param p The point at which to calculate the intensity.
    @return The intensity of the light.
    */
   public Color getIntensity(Point p) {
       Color iO = super.getIntensity(p);

       double dotProd = alignZero(direction.dotProduct(getL(p)));
       if (isZero(dotProd)) {
           return Color.BLACK;
       }

       double max = Math.max(dotProd, 0);
       //Color iL = super.getIntensity(p);
       return iO.scale(max);
   }
}
}

