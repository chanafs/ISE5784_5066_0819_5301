package lighting;

import primitives.Color;

/**
 * Abstract class representing a generic light source.
 * This class provides the basic framework for defining light sources with an intensity attribute.
 */
public abstract class Light {

    /**
     * The intensity of the light, represented as a Color.
     * This attribute can affect how bright the light appears and its color tint.
     */
    protected Color intensity;

    /**
     * Protected constructor for initializing the Light object with a specified intensity.
     *
     * @param intensity The intensity of the light, represented as a Color.
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * Gets the intensity of the light.
     *
     * @return The intensity of the light, represented as a Color.
     */
    public Color getIntensity() {
        return intensity;
    }
}
