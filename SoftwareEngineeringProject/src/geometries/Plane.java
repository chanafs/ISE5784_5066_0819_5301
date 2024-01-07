package geometries;
import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry { 

	private Point q; 
	private Vector normal; 
	/*
	 * constructor*/
	public Plane(Point q, Vector normal) {
		super();//needs?
		this.q = q;
		this.normal = normal;
	}
	/*
	 * constructor*/
	public Plane(Point a, Point b, Point c) {
		super();
		//FOR NOW: 
		this.q = a; //arbitrary
		this.normal = null ;
	}
	public Vector getNormal() {
		return this.normal; 
	}
	public Vector getNormal(Point p) {
		return this.normal; 
	}
	
}
