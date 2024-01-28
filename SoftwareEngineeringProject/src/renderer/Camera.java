/**
 * 
 */
package renderer;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
import static primitives.Util.isZero;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/*
 * public class Camera implements Cloneable 
 * uses Builder Design Pattern
 * */
public class Camera implements Cloneable{
//private final static double DELTA = 0.000001;
private Point location; 
private Vector to; 
private Vector up; 
private Vector right; 

public double width=1; 
public double height=1; 
public double distance=1; 
/*
 * initializing all values to 0 for now 
 * */
private Camera() {
	this.location= new Point(0,0,0); 
	this.to = new Vector(0, 0, 0); 
	this.up=new Vector(0, 0, 0);
	this.right=new Vector(0, 0, 0);
}
/*
 * getters for Camera
 * */
public Point getLocation() {
	return location;
}
public Vector getTo() {
	return to;
}

public Vector getUp() {
	return up;
}

public Vector getRight() {
	return right;
}
public static Builder getBuilder() {
return new Builder(); 
}
public double getWidth() {
	return width;
}
public double getHeight() {
	return height;
}
public double getDistance() {
	return distance;
}
public void setLocation(Point location) {
	this.location = location;
}
public Camera build(){
	Camera camera = new Camera();
	return (Camera) camera.clone(); 
        	//assertEquals(0.0, this.c.getWidth(), DELTA, "Width can't be zero");
        	//assertEquals(0.0, this.c.getHeight(), DELTA, "Height can't be zero");
        	//assertEquals(0.0, this.c.getDistance(), DELTA, "Distance can't be zero");		
    }
/*
 * constructRay maps pixel indicies to points on the image plane and constructs a ray from the camera location to those points
 * parameters i and j - row and column of pixel accordingly
- Nx represents the number of columns (row width) and Ny represents the number of rows (column height) 
 */
public Ray constructRay(int nX, int nY, int j, int i){
Point Pc = location.add(to.scale(distance));

double Rx = width / nX;
double Ry = height / nY;

Point Pij = Pc;

double Xj = (j - (nX - 1) / 2d) * Rx;
double Yi = -(i - (nY - 1) / 2d) * Ry;

if (isZero(Xj) && isZero(Yi)) {
    return new Ray(location, Pij.subtract(location));
}
if (isZero(Xj)) {
    Pij = Pij.add(up.scale(Yi));
    return new Ray(location, Pij.subtract(location));
}
if (isZero(Yi)) {
    Pij = Pij.add(right.scale(Xj));
    return new Ray(location, Pij.subtract(location));
}

Pij = Pij.add(right.scale(Xj).add(up.scale(Yi)));
return new Ray(location, Pij.subtract(location));
}

/*
 * Builder class nested in Camera 
 * */
public static class Builder{
	private final Camera c; 
	
	/*
	 * creates new camera using the camera object passed
	 * */
	public Builder(Camera copy) {
		this.c=copy; 
}
	/*
	 * default constructor 
	 * */
	public Builder() {
		this.c =new Camera();  
}
	public Builder setLocation(Point p) {
		c.location=p; 
		return this; 
}
	public Builder setDirection(Vector forward, Vector up) {
		if (!isZero(forward.dotProduct(up))) {
            throw new IllegalArgumentException("forward and up are not orthogonal");
        }
		forward=forward.normalize(); 
		up=up.normalize(); 
		this.c.to=forward; 
		this.c.up=up; 
		return this; 
}
	public Camera setViewPlaneSize(double w, double h) {
        c.width = w;
        c.height = h;
        return this.c;
}
	public Builder setViewPlaneDistance(double d) {
        c.distance = d;
        return this;
        //distance between camera and the view plane
	}
	
	public void setDistance(double distance) {
		this.c.distance = distance;
	}
    public void cameraBuilder(Point l, Vector t, Vector u) {
        c.location = l;
        if (!isZero(t.dotProduct(u))) {
            throw new IllegalArgumentException("Vectors are not orthogonal");
        }

        c.to = t.normalize();
        c.up = u.normalize();
       c.right = c.to.crossProduct(c.up);
    }

}

}