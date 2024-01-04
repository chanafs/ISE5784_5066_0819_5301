package primitives;

public class Ray {
	//part of a line that has a fixed starting point but does not have an endpoint
	private Point head;
	private Vector direction;
	
	public boolean equals(Object obj) {return false;}
	
	public Ray(Point h, Vector d) {
		super(); //need?
		
		Vector vec= d.normalize(); 
		this.head = h;
		this.direction = vec;
	}

	@Override
	public String toString() {
		return "Ray []";
	}

}
