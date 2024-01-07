package primitives;
import java.lang.Math;

public class Point {
	
	protected Double3 xyz;
	public static final Point ZERO = new Point(0, 0, 0);
	
	/*
	 * constructor */
	protected Point(Double3 d3) {
		this.xyz = d3;
	}
	/*
	 * constructor */
	public Point(double a, double b, double c) {
		this.xyz = new Double3(a, b, c);
	}
	/*
	 * 
	 returns true if the object passed is equal to the Point*/
	public boolean equals(Object obj) {
		if(obj instanceof Point other)
		 return (this.xyz.equals(other.xyz));
		else
			return false;
	}
	
	@Override
	public String toString() {
		return "Point [xyz=" + xyz + "]";
	}

	/*
	 * returns the sum of the two Points*/
	public Point add(Vector other) {
		 Double3 sum = this.xyz.add(other.xyz);
		 return new Point(sum);
		}
	/*
	 * returns the difference of the two Points*/
	public Vector subtract(Point other) {
		 Double3 diff =  this.xyz.subtract(other.xyz);
		 return new Vector(diff);
	}
	/*
	 * returns the distance between the two Points*/
	public double distance(Point other) 
	{
		return (Math.sqrt(distanceSquared(other)));
	}
	/*
	 * returns the distance squared between the two Points*/
	public double distanceSquared(Point other) {
		double x = (other.xyz.d1 - this.xyz.d1);
		double y = (other.xyz.d2 - this.xyz.d2);
		double z = (other.xyz.d3 - this.xyz.d3);
		x = x*x;
		y = y*y;
		z = z*z;
		return (x+y+z);
	}
	


}
