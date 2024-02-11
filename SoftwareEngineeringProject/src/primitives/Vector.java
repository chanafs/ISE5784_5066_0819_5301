package primitives;
import java.lang.Math;
/*
 * Vectors inherits from Point */

public class Vector extends Point {
	
	  /*
     * Constructs a Vector object with the specified x, y, and z components.
     *
     * @param x the x component of the vector
     * @param y the y component of the vector
     * @param z the z component of the vector
     * @throws IllegalArgumentException if the vector's components are all zero
     */
	
	public Vector(double x, double y, double z) {
		super(new Double3(x, y, z));
		if(this.xyz.equals(Double3.ZERO))
			throw new IllegalArgumentException("Vector cannot be the zero vector");
	}
	  /*
     * Constructs a Vector object from a Double3 object.
     * @param d: the Double3 object representing the vector's components
     */
	public Vector(Double3 d) {
		super(d);
		if(d.equals(Double3.ZERO))
				throw new IllegalArgumentException("Vector cannot be the zero vector");
	}
	/*
     * Checks if the vector is equal to the specified object.
     *
     * @param obj: the object to compare with
     * @return true if the object is equal to the vector, false otherwise
     */
	public boolean equals (Object obj) {
		
		if(obj instanceof Vector other)
			return((this.xyz).equals(other.xyz));
		else
			return false;
	}
	/*
	 * returns the length squared of the Vector
	 */
	public double lengthSquared() {
		return (((this.xyz.d1)*(this.xyz.d1)) + ((this.xyz.d2)*(this.xyz.d2)) + ((this.xyz.d3)*(this.xyz.d3)));
	}
	/*
	 * returns the length of the Vector
	 */
	public double length() {
		return Math.sqrt(this.lengthSquared());
	}
	/*
	 * adds one Vector to another
	 */
	public Vector add(Vector other) {
		Double3 sum = this.xyz.add(other.xyz);
        return new Vector(sum);
	}
	/*
	 * scales a Vector and returns the scaled Vector 
	 */
	public Vector scale (double d) {
		Double3 ans = this.xyz.scale(d);
        return new Vector(ans); 
		}
	/*
	 * returns the dotProduct of two Vectors 
	 */
	public double dotProduct(Vector u) {
		return (u.xyz.d1 * this.xyz.d1) + (u.xyz.d2 * this.xyz.d2) + (u.xyz.d3 * this.xyz.d3);
	}
	
	/*
	 * returns the crossProduct of two Vectors 
	 */
	public Vector crossProduct(Vector v) {
		
        double c1 = (this.xyz.d2) * (v.xyz.d3) - (this.xyz.d3) * (v.xyz.d2);
        double c2 = (this.xyz.d3) * (v.xyz.d1) - (this.xyz.d1) * (v.xyz.d3);
        double c3 = (this.xyz.d1) * (v.xyz.d2) - (this.xyz.d2) * (v.xyz.d1);

        return new Vector(c1, c2, c3);
	}
	/*
	 * returns a normalized Vector
	 */
	public Vector normalize() 
	{
		double m=length();
        this.xyz.d1/=m;
        this.xyz.d2/=m;
        this.xyz.d3/=m;
        return this;
	}
	/*
	 * returns the difference of the two Points
	 */
	public Vector subtract(Point other) {
		 Double3 diff =  this.xyz.subtract(other.xyz);
		 return new Vector(diff);
	}


}
