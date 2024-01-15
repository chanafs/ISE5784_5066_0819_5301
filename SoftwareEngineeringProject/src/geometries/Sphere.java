package geometries;

import primitives.Point;
import primitives.Vector;

public class Sphere extends RadialGeometry{
	private Point center; 
	 /*
	  * constructor*/
	public Sphere(double r, Point c) {
		super(r); //sets radius by sending it to constructor of RadialGeometry
		this.setCenter(c);
	}

	public Vector getNormal(Point p) {
		return (p.subtract(center)).normalize();
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}
	
}
