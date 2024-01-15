package geometries;
import primitives.Util;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Tube extends RadialGeometry{

	protected Ray axis; 
	/*
	 * constructor*/
	public Tube(double radius, Ray axis) {
		super(radius);
		this.axis = axis;
	}
	
	/*
	 * getNormal(Point p) computes and returns the normal vector at p on the surface of the tube, given its axis 
	 * */
	public Vector getNormal(Point p) {
        double t = axis.getDirection().dotProduct(p.subtract(axis.getHead()));
        Point o = axis.getHead().add(axis.getDirection().scale(t));
        return p.subtract(o).normalize();
    }

}
