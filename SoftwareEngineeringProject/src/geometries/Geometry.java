package geometries;
import primitives.*; 

public abstract class Geometry extends Intersectable{
	public abstract Vector getNormal(Point p); 
	private Color emission= Color.BLACK; 
	private Material material = new Material();
	 
	/*
	 * getEmisson returns the emission
	 * */
	 public Color getEmission() {
		return emission; 
	}
	/*
	 * setEmisson sets the emission
	 * @param c
	 * */
	public Geometry setEmission(Color c) {
		this.emission=c; 
		return this; 
	}
	
	/*
	 * getMaterial gets the material
	 * */
	public Material getMaterial() {
		return material;
	}
	
	/*
	 * setEmisson sets the emission
	 * @param c
	 * */
	public Geometry setMaterial(Material m) {
		this.material=m; 
		return this; 
	}
	
	
}
