package primitives;
import java.lang.Math;
public class Point {
	/*
    xyz is a variable of type Double3 which contains three coordinates for the point
     */
	public Double3 xyz;
	public static final Point ZERO = new Point(0, 0, 0);
	
	/*
     * Constructs and initializes a point from the specified Double3 object.
     * @param point: Double3 containing the x, y, and z coordinates of the point
     */
	protected Point(Double3 point) {
		this.xyz = point;
	}
	/*
     * Constructs and initializes a point with the specified x, y, and z coordinates.
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     * @param z the z coordinate of the point
     */
	public Point(double x, double y, double z) {
		this.xyz = new Double3(x, y, z);
	}
	/*
	 *equals returns true if the object passed is equal to the Point
	 *@param obj: the reference object with which to compare
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Point other)
		 return (this.xyz.equals(other.xyz));
		else
			return false;
	}
	 /*
     * Returns a string representation of the point.
     */
	@Override
	public String toString() {
		return "Point [xyz=" + xyz + "]";
	}

	/*
	 * returns the sum of the two Points
	 */
	public Point add(Vector other) {
		 Double3 sum = this.xyz.add(other.xyz);
		 return new Point(sum);
		}
	/*
	 * returns the difference of the two Points
	 */
	public Vector subtract(Point other) {
		 Double3 diff =  this.xyz.subtract(other.xyz);
		 return new Vector(diff);
	}
	/*
	 * returns the distance between the two Points
	 */
	public double distance(Point other) 
	{
		return (Math.sqrt(distanceSquared(other)));
	}
	/*
	 * returns the distance squared between the two Points
	 */
	public double distanceSquared(Point other) {
		double x = (other.xyz.d1 - this.xyz.d1);
		double y = (other.xyz.d2 - this.xyz.d2);
		double z = (other.xyz.d3 - this.xyz.d3);
		x = x*x;
		y = y*y;
		z = z*z;
		return (x+y+z);
	}
	
	public Double3 getxyz() {
		return xyz; 
	}
	

}
