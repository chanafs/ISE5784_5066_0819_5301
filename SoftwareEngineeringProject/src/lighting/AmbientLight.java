/**
 * 
 */
package lighting;
import primitives.Color;
import primitives.Double3;
/*
 * AmbientLight class represents the ambient light in a scene.
 * Ambient light is light that is scattered and reflected throughout a scene
 * This class defines the color and intensity of the ambient light.
 */
public class AmbientLight {
	final public Color intensity; 
	public static final AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO); 
    
	/*
     * Constructor
     * @param Ia intensity color
     * @param Ka constant for intensity
     */
	public AmbientLight(Color Ia, Double3 Ka) {
        intensity = Ia.scale(Ka);
    }
	public AmbientLight(Color Ia, double Ka) {
        intensity = Ia.scale(Ka);
    }
	public Color getIntensity() {
        return intensity;
    }
}
