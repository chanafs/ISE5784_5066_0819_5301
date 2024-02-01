/**
 * 
 */
package lighting;
import primitives.Color;
import primitives.Double3;
/**
 * 
 */
public class AmbientLight {
	final public Color intensity; 
	public static final AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO); 
    
	/**
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
