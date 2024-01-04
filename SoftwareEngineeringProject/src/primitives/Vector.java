package primitives;

public class Vector extends Point {
	
	public Vector(double a, double b, double c) {
		super(new Double3(a, b, c));
		if(this.doub3.equals(Double3.ZERO))
			throw new IllegalArgumentException("Vector cannot be the zero vector");
	}
	
	public Vector(Double3 d3) {
		super(d3);
		if(d3.equals(Double3.ZERO))
				throw new IllegalArgumentException("Vector cannot be the zero vector");
	}
	
	public boolean equals (Object o) {return false;}
	public double lengthSquared() {return 2;}
	public double length() {return 2;}
	
	public Vector add(Vector other) {
		//are we supposed to use points add method?
		
		Double3 sum = this.doub3.add(other.doub3);
        return new Vector(sum);
        
        /*
		double x = other.doub3.d1 + this.doub3.d1;
		double y = other.doub3.d2 + this.doub3.d2;other
		double z = other.doub3.d3 + this.doub3.d3;
		return (new Vector(x, y, z));
		*/
	}
	
	public Vector scale (double d) {
		Double3 ans = this.doub3.scale(d);
        return new Vector(ans); 
		}
	public Vector dotProduct(Vector other) {
		double x = other.doub3.d1 * this.doub3.d1;
		double y = other.doub3.d2 * this.doub3.d2;
		double z = other.doub3.d3 * this.doub3.d3;
		return new Vector(x, y, z);
	}
	public Vector crossProduct(Vector v) {
		double a1 = this.doub3.d1;
        double a2 = this.doub3.d2;
        double a3 = this.doub3.d3;

        double b1 = v.doub3.d1;
        double b2 = v.doub3.d2;
        double b3 = v.doub3.d3;

        double c1 = a2 * b3 - a3 * b2;
        double c2 = a3 * b1 - a1 * b3;
        double c3 = a1 * b2 - a2 * b1;

        return new Vector(c1, c2, c3);
	}
	public Vector normalize() 
	{
		double length = distance(new Point(this.doub3));
		Vector normalizedVector = 
				new Vector(
				this.doub3.d1/length, 
				this.doub3.d2/length, 
				this.doub3.d3/length
				);
		return normalizedVector;
	}

}
