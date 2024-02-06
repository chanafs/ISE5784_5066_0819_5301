/**
 * 
 */
package renderer;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
import static primitives.Util.isZero;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;
import java.util.MissingResourceException;
import scene.*; 
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

private ImageWriter imageWriter; 
private RayTraceBase rayTracer; 

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
    return camera;
	//return (Camera) camera.clone(); 
	/*
	 * In the Builder inside the build method, add a check that both fields has an object, throw an
appropriate exception if some object is missing. (like stage 4 instruction).

	 * 
	 * */
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

public RayTraceBase getRayTracer() {
	return rayTracer;
}
public void setRayTracer(RayTraceBase rayTracer) {
	this.rayTracer = rayTracer;
}

public ImageWriter getImageWriter() {
	return imageWriter;
}
public void setImageWriter(ImageWriter imageWriter) {
	this.imageWriter = imageWriter;
}
private Color castRay(int nX, int nY, int row, int column) {
    // construct a ray for each pixel
    Ray ray = this.constructRay(nX, nY, column, row);
    // calculate the color
    Color color = this.rayTracer.traceRay(ray);
    return color;

	/*
	 * Add a castRay method that receives the resolution and the pixel number (see lab’s presentation
for details). Method is void, with private permission.
o Method will create a ray through the center of pixel using the constructRay method, will
invoke the traceRay of the ray tracer to calculate the ray’s color and, at the end, will color
the pixel using writePixel method.
	 * */
}

/*
 * Builder class nested in Camera 
 * */
public static class Builder{
	private final Camera camera; 
	
	/*
	 * creates new camera using the camera object passed
	 * */
	public Builder(Camera copy) {
		this.camera=copy; 
}
	/*
	 * default constructor 
	 * */
	public Builder() {
		this.camera =new Camera();  
}
	public Builder setLocation(Point p) {
		camera.location=p; 
		return this; 
}
	public Builder setDirection(Vector forward, Vector up) {
		if (!isZero(forward.dotProduct(up))) {
            throw new IllegalArgumentException("forward and up are not orthogonal");
        }
		forward=forward.normalize(); 
		up=up.normalize(); 
		this.camera.to=forward; 
		this.camera.up=up; 
		return this; 
}
	public Camera setViewPlaneSize(double w, double h) {
        camera.width = w;
        camera.height = h;
        return this.camera;
}
	public Builder setViewPlaneDistance(double d) {
        camera.distance = d;
        return this;
        //distance between camera and the view plane
	}
	
	public void setDistance(double distance) {
		this.camera.distance = distance;
	}
    public void cameraBuilder(Point l, Vector t, Vector u) {
        camera.location = l;
        if (!isZero(t.dotProduct(u))) {
            throw new IllegalArgumentException("Vectors are not orthogonal");
        }

        camera.to = t.normalize();
        camera.up = u.normalize();
        camera.right = camera.to.crossProduct(camera.up);
    }
    public Camera getCamera() {
    	return camera; 
    }
}
/*
*implement the method renderImage to loop over all the ViewPlane’s pixels. For each pixel it will
construct a ray using the caseRay method.

**/
public void renderImage() {
	Camera camera1=null; 
    try {
        if (imageWriter == null) {
            throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");
        }
        if (rayTracer == null) {
            throw new MissingResourceException("missing resource", RayTraceBase.class.getName(), "");
        }

        //rendering the image
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                Ray ray = camera1.constructRay(nX, nY, j, i); //CAMERA1 is NULL???? 
                Color pixelColor = rayTracer.traceRay(ray); //uses rayTracer
                imageWriter.writePixel(j, i, pixelColor);
            }
        }
    } catch (MissingResourceException e) {
        throw new UnsupportedOperationException("Not implemented yet" + e.getClassName());
 }
    }
/*
 * printGrid creates a grid of lines
 * want to color the pixels where the grid appears in them, leave the other pixels alone
 * */
public void printGrid(int interval, Color color) {
    int nX = imageWriter.getNx();
    int nY = imageWriter.getNy();
    for (int i = 0; i < nY; i++) {
        for (int j = 0; j < nX; j++) {
            if (i % interval == 0 || j % interval == 0) {
                imageWriter.writePixel(j, i, color);
            }
        }
    }

} 
public void writeToImage() {
    imageWriter.writeToImage();
}


}
