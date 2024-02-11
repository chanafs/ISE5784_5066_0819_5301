package geometries;
import primitives.*; 

public abstract class Geometry extends Intersectable{
	public abstract Vector getNormal(Point p); 
	 protected Color emission= Color.BLACK; 
	 
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
	public Color setEmission(Color c) {
		this.emission=c; 
		return this.emission; 
	}
}
