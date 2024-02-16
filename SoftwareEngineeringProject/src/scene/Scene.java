/**
 * 
 */
package scene;

/**

 * 
 */
import lighting.AmbientLight;


import lighting.LightSource;
import geometries.Geometries;
import primitives.Color;
import java.util.List;
import java.util.LinkedList;

public class Scene {
    public String name;
    public Color background = Color.BLACK;
    public AmbientLight ambientlight= AmbientLight.NONE; 
    public Geometries geometries = new Geometries();//null;
    public List<LightSource> lights = new LinkedList<LightSource>();

    public Scene(String n) {
        setName(n);
        geometries= new Geometries();
    }
    
    /**
     * Sets the background color of the scene.
     *
     * @param background the background color
     * @return the SceneBuilder instance for method chaining
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return  this;
    }
    
    /**
     * Sets the light sources of the scene.
     *
     * @param lights the light sources of the scene
     * @return the SceneBuilder instance for method chaining
     */
    public Scene setLights(List<LightSource> lights) {
    	this.lights = lights;
        return this;
    }

    
    /**
     * Sets the ambient light of the scene.
     *
     * @param ambientLight the ambient light
     * @return the SceneBuilder instance for method chaining
     */
    public Scene setAmbientLight(AmbientLight ambientlight) {
        this.ambientlight = ambientlight;
        return this;
    }

    /**
     * Sets the geometries of the scene.
     *
     * @param geometries the geometries of the scene
     * @return the SceneBuilder instance for method chaining
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return  this;
    }

	public void setName(String name) {
		this.name = name;
	}
	public Geometries getGeometries() {
	return geometries; 
}
	public Color getBackground() {
		return background; 
	}
	public AmbientLight getAmbientlight() {
		return this.ambientlight; 
	}
	
	public List<LightSource> getLights() {
        return lights;
    }

}