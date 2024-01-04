package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry { //correct notation??

	private Point q; 
	private Vector normal; 
	
	public Plane(Point q, Vector normal) {
		super();//needs?
		this.q = q;
		this.normal = normal;
	}
	public Plane(Point a, Point b, Point c) {
		super();//needs?
		
		//FOR NOW: 
		this.q = a; //arbitrary
		this.normal = null ;
	}
	public Vector getNormal() {
		return this.normal; 
	}
	public Vector getNormal(Point p) {
		return this.normal; //unrelated to p?? 
	}
	
}
