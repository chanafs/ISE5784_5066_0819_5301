package primitives;
import java.lang.Math;

public class Point {
	
	protected Double3 xyz;
	public static final Point ZERO = new Point(0, 0, 0);
	
	protected Point(Double3 d3) {
		this.xyz = d3;
	}
	
	public Point(double a, double b, double c) {
		this.xyz = new Double3(a, b, c);
	}

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

	public Point add(Vector other) {
		
		//do we need to send other to point constructor ?
		 Double3 sum = this.xyz.add(other.xyz);
		 return new Point(sum);
		}
	
	public Vector subtract(Point other) {
		 Double3 diff =  this.xyz.subtract(other.xyz);
		 return new Vector(diff);
		 
		 /*
		  * public Vector subtract(Point other) {
        Double3 difference = this.xyz.subtract(other.xyz);
        return new Vector(difference);
    }
		  */
		 
	}
	
	public double distance(Point other) 
	{
		return (Math.sqrt(distanceSquared(other)));
	}
	
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
