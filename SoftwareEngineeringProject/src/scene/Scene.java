/**
 * 
 */
package scene;

/**
 * 
 */
import lighting.AmbientLight;
import geometries.Geometries;
import primitives.Color;

public class Scene {
    public String name;
    public Color background = Color.BLACK;
    public AmbientLight ambientlight= AmbientLight.NONE; 
    public Geometries geometries = new Geometries();//null;

    public Scene(String n) {
        setName(n);
        geometries= new Geometries();
    }
    public Scene setBackground(Color background) {
        this.background = background;
        return  this;
    }

    public Scene setAmbientLight(AmbientLight ambientlight) {
        this.ambientlight = ambientlight;
        return this;
    }

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
}