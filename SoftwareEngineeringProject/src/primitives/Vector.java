package primitives;
import java.lang.Math;

public class Vector extends Point {
	
	public Vector(double a, double b, double c) {
		super(new Double3(a, b, c));
		if(this.xyz.equals(Double3.ZERO))
			throw new IllegalArgumentException("Vector cannot be the zero vector");
	}
	
	public Vector(Double3 d3) {
		super(d3);
		if(d3.equals(Double3.ZERO))
				throw new IllegalArgumentException("Vector cannot be the zero vector");
	}
	
	public boolean equals (Object o) {
		
		if(o instanceof Vector other)
			return((this.xyz).equals(other.xyz));
		else
			return false;
	}
	
	public double lengthSquared() {
		return ((this.xyz.d1)*(this.xyz.d1) + (this.xyz.d2)*(this.xyz.d2) + (this.xyz.d3)*(this.xyz.d3));
	}
	
	public double length() {
		return Math.sqrt(this.lengthSquared());
	}
	
	public Vector add(Vector other) {
		//are we supposed to use points add method?
		
		Double3 sum = this.xyz.add(other.xyz);
        return new Vector(sum);
        
        /*
		double x = other.xyz.d1 + this.xyz.d1;
		double y = other.xyz.d2 + this.xyz.d2;other
		double z = other.xyz.d3 + this.xyz.d3;
		return (new Vector(x, y, z));
		*/
	}
	
	public Vector scale (double d) {
		Double3 ans = this.xyz.scale(d);
        return new Vector(ans); 
		}
	public double dotProduct(Vector other) {
		return (other.xyz.d1 * this.xyz.d1) + (other.xyz.d2 * this.xyz.d2) + (other.xyz.d3 * this.xyz.d3);
	}
	public Vector crossProduct(Vector v) {
		
        double c1 = (this.xyz.d2) * (v.xyz.d3) - (this.xyz.d3) * (v.xyz.d2);
        double c2 = (this.xyz.d3) * (v.xyz.d1) - (this.xyz.d1) * (v.xyz.d3);
        double c3 = (this.xyz.d1) * (v.xyz.d2) - (this.xyz.d2) * (v.xyz.d1);

        return new Vector(c1, c2, c3);
	}
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

}
