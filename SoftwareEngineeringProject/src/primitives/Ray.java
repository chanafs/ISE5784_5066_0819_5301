package primitives;

public class Ray {
	private Point head;
	private Vector direction;
	
	public boolean equals(Object obj) {return false;}
	
	public final Point getHead() {
		return head;
	}
	public void setHead(Point head) {
		this.head = head;
	}
	public final Vector getDirection() {
		return direction;
	}
	public void setDirection(Vector direction) {
		this.direction = direction;
	}
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
