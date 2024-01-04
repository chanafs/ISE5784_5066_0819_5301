package primitives;
import java.lang.Math;

public class Point {
	
	protected Double3 doub3;
	
	protected Point(Double3 d3) {
		this.doub3 = d3;
	}
	
	public Point(double a, double b, double c) {
		this.doub3 = new Double3(a, b, c);
	}

	public boolean equals(Object obj) {return false;}

	@Override
	public String toString() {
		return "Point []";
	}
	
	public Point add(Vector other) {
		
		//do we need to send other to point constructor ?
		 Double3 sum = this.doub3.add(other.doub3);
		 return new Point(sum);
		}
	
	public Vector subtract(Point other) {
		 Double3 diff = this.doub3.subtract(other.doub3);
		 return new Vector(diff);
		 
		 /*
		  * public Vector subtract(Point other) {
        Double3 difference = this.doub3.subtract(other.doub3);
        return new Vector(difference);
    }
		  */
		 
	}
	
	public double distance(Point other) 
	{
		return (Math.sqrt(distanceSquared(other)));
	}
	
	public double distanceSquared(Point other) {
		double x = (other.doub3.d1 - this.doub3.d1);
		double y = (other.doub3.d2 - this.doub3.d2);
		double z = (other.doub3.d3 - this.doub3.d3);
		x *= x;
		y *= y;
		z *= z;
		return (x+y+z);
	}
	


}
