package primitives;

import java.util.List;

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
	public Ray(Point h, Vector vector) {
		super(); 
		Vector vec= vector.normalize(); 
		this.head = h;
		this.direction = vec;
	}

	@Override
	public String toString() {
		return "Ray []";
	}
	/*
	 * getPoint calculates point on the ray: 𝑷 = 𝑷𝟎 + 𝒕∙𝒗
	 * */
	public Point getPoint(double t) {

	        return this.head.add(direction.scale(t)); 

	    }
	/**
     * find the closest Point to Ray origin
     * @param pointsList intersections point List
     * @return closest point
     */
    public Point findClosestPoint(List<Point> pointsList){
        Point result =null;
        double closestDistance = Double.MAX_VALUE;

        if(pointsList== null){
            return null;
        }

        for (Point p: pointsList) {
            double temp = p.distance(head);
            if(temp < closestDistance){
                closestDistance =temp;
                result =p;
            }
        }

        return  result;
    }
}
