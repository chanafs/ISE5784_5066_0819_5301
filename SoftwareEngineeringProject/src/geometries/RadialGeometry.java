package geometries;

public abstract class RadialGeometry extends Geometry {

	protected double radius;
	/*
	 * constructor
	 */
	public RadialGeometry(double radius) {
		super(); 
		this.radius = radius;
	} 
}
