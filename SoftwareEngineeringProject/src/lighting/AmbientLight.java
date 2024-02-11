package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * Represents the ambient light in a scene, providing a base level of light
 * that is neither coming from a specific direction nor from a specific source.
 * Ambient light is assumed to be equally scattered and reflected throughout
 * the entire scene, defining the color and intensity of the ambient light.
 */
public class AmbientLight extends Light {

    /**
     * A constant representing no ambient light, using black color and zero intensity.
     */
    public static final AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);

    /**
     * Constructs an AmbientLight with a specified intensity color and a scalar
     * for intensity.
     *
     * @param Ia The color intensity of the ambient light.
     * @param Ka A scalar (Double3) representing the coefficient for scaling the intensity.
     */
    public AmbientLight(Color Ia, Double3 Ka) {
        super(Ia);
        intensity = Ia.scale(Ka);
    }

    /**
     * Constructs an AmbientLight with a specified intensity color and a scalar
     * for intensity.
     *
     * @param Ia The color intensity of the ambient light.
     * @param Ka A scalar (double) representing the coefficient for scaling the intensity.
     */
    public AmbientLight(Color Ia, double Ka) {
        super(Ia);
        intensity = Ia.scale(Ka);
    }
}
