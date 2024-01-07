package geometries;

import primitives.Point;
import primitives.Vector;

public class Sphere extends RadialGeometry{
	private Point center; 
	 /*
	  * constructor*/
	public Sphere(double r, Point c) {
		super(r);
		this.center = c;
	}

	public Vector getNormal(Point p) {
		return null; 
	}
	
}
