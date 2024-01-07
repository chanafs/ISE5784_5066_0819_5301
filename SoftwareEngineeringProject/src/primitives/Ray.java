package primitives;

public class Ray {
	private Point head;
	private Vector direction;
	
	public boolean equals(Object obj) {return false;}
	/*
	 * constructor*/
	public Ray(Point h, Vector d) {
		super(); 
		Vector vec= d.normalize(); 
		this.head = h;
		this.direction = vec;
	}

	@Override
	public String toString() {
		return "Ray []";
	}

}
