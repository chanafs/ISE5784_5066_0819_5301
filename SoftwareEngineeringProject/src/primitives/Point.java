package primitives;
import java.lang.Math;

public class Point {
	
	protected Double3 xyz;
	
	protected Point(Double3 d3) {
		this.xyz = d3;
	}
	
	public Point(double a, double b, double c) {
		this.xyz = new Double3(a, b, c);
	}

	public boolean equals(Object obj) {return false;}

	@Override
	public String toString() {
		return "Point []";
	}
	
	public Point add(Vector other) {
		
		//do we need to send other to point constructor ?
		 Double3 sum = this.xyz.add(other.xyz);
		 return new Point(sum);
		}
	
	public Vector subtract(Point other) {
		 Double3 diff = this.xyz.subtract(other.xyz);
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
		x *= x;
		y *= y;
		z *= z;
		return (x+y+z);
	}
	


}
