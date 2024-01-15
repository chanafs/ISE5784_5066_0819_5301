package primitives;
import java.lang.Math;
/*
 * Vectors inherits from Point */

public class Vector extends Point {
	/*
	 * constructor*/
	public Vector(double a, double b, double c) {
		super(new Double3(a, b, c));
		if(this.xyz.equals(Double3.ZERO))
			throw new IllegalArgumentException("Vector cannot be the zero vector");
	}
	/*
	 * constructor*/
	public Vector(Double3 d3) {
		super(d3);
		if(d3.equals(Double3.ZERO))
				throw new IllegalArgumentException("Vector cannot be the zero vector");
	}
	/*
	 *returns true if the object passed is equal to the Vector that called equals*/
	public boolean equals (Object o) {
		
		if(o instanceof Vector other)
			return((this.xyz).equals(other.xyz));
		else
			return false;
	}
	/*
	 * returns the length squared of the Vector*/
	public double lengthSquared() {
		return ((this.xyz.d1)*(this.xyz.d1) + (this.xyz.d2)*(this.xyz.d2) + (this.xyz.d3)*(this.xyz.d3));
	}
	/*
	 * returns the length of the Vector*/
	public double length() {
		return Math.sqrt(this.lengthSquared());
	}
	/*
	 * adds one Vector to another*/
	public Vector add(Vector other) {
		Double3 sum = this.xyz.add(other.xyz);
        return new Vector(sum);
	}
	/*
	 * scales a Vector and returns the scaled Vector */
	public Vector scale (double d) {
		Double3 ans = this.xyz.scale(d);
        return new Vector(ans); 
		}
	/*
	 * returns the dotProduct of two Vectors */
	public double dotProduct(Vector other) {
		return (other.xyz.d1 * this.xyz.d1) + (other.xyz.d2 * this.xyz.d2) + (other.xyz.d3 * this.xyz.d3);
	}
	/*
	 * returns the crossProduct of two Vectors */
	public Vector crossProduct(Vector v) {
		
        double c1 = (this.xyz.d2) * (v.xyz.d3) - (this.xyz.d3) * (v.xyz.d2);
        double c2 = (this.xyz.d3) * (v.xyz.d1) - (this.xyz.d1) * (v.xyz.d3);
        double c3 = (this.xyz.d1) * (v.xyz.d2) - (this.xyz.d2) * (v.xyz.d1);

        return new Vector(c1, c2, c3);
	}
	/*
	 * returns a normalized Vector*/
	public Vector normalize() 
	{
		double l = this.length();
		Vector normalizedVector = 
				new Vector(
				this.xyz.d1/l, 
				this.xyz.d2/l, 
				this.xyz.d3/l
				);
		return normalizedVector;
	}
	/*
	 * returns the difference of the two Points*/
	public Vector subtract(Point other) {
		 Double3 diff =  this.xyz.subtract(other.xyz);
		 return new Vector(diff);
	}


}
