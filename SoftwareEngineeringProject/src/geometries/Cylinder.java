package geometries;
import primitives.*; //extra? 

public class Cylinder extends Tube{
	
	private double height; 
	
	public Vector getNormal(Point p) {
		return null; 
	}
	
	Ray axis; //public? 

	public Cylinder(double radius, Ray axis, double height, Ray axis2) {
		super(radius, axis);
		this.height = height;
		axis = axis2;
	}

		
}
