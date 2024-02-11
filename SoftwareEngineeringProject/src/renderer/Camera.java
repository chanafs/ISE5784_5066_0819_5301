package renderer;
import static primitives.Util.isZero;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import java.util.MissingResourceException;

public class Camera implements Cloneable{

private Point location; 
private Vector vTo; // The vector pointing in the direction the camera is facing
private Vector vUp; // The vector pointing up from the camera's position
private Vector vRight; // The vector pointing to the right of the camera's position

// Variables for the view plane
private double width; // The width of the view plane
private double height; // The height of the view plane
private double distance; // The distance from the camera to the view plane

private ImageWriter imageWriter; 
private RayTraceBase rayTracer; 

/*
 * constructor; sets all values to zero for now  
 * */
/**private Camera() {
	this.location= new Point(0,0,0); 
	this.vTo = new Vector(0, 0, 0); 
	this.vUp=new Vector(0, 0, 0);
	this.vRight=new Vector(0, 0, 0);
}*/

public Camera(Point p0, Vector v1, Vector v2) {
    this.location = p0;
    this.vTo = v1.normalize();
    this.vUp = v2.normalize();

    if (!isZero(vTo.dotProduct(vUp))) {
        throw new IllegalArgumentException("vTo and vUp are not orthogonal");
    }

    vRight = this.vTo.crossProduct(this.vUp);
}
public Point getLocation() {
	return location;
}
public Vector getVto() {
	return vTo;
}

public Vector getVup() {
	return vUp;
}

public Vector getvRight() {
	return vRight;
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
/*
     Constructs a Ray object representing the ray that would be cast from the camera's position through the specified
     pixel on the view plane.
     @param nX the number of columns
     @param nY the number of rows
     @param i
     @param j
*/
public Ray constructRay(int nX, int nY, int j, int i) {
    // Calculate the camera position on the view plane
    Point Pc = location.add(vTo.scale(distance));

    // Calculate the pixel dimensions on the view plane
    double Rx = width / nX;
    double Ry = height / nY;

    // Set the initial point on the view plane to Pc
    Point Pij = Pc;

    // Calculate the coordinates of the pixel on the view plane
    double Xj = (j - (nX - 1) / 2d) * Rx;
    double Yi = -(i - (nY - 1) / 2d) * Ry;

    // Check if the pixel is at the center of the view plane
    if (isZero(Xj) && isZero(Yi)) {
        return new Ray(location, Pij.subtract(location));
    }

    // Check if the pixel is on the horizontal axis of the view plane
    if (isZero(Xj)) {
        Pij = Pij.add(vUp.scale(Yi));
        return new Ray(location, Pij.subtract(location));
    }

    // Check if the pixel is on the vertical axis of the view plane
    if (isZero(Yi)) {
        Pij = Pij.add(vRight.scale(Xj));
        return new Ray(location, Pij.subtract(location));
    }

    // Calculate the final point on the view plane for the specified pixel
    Pij = Pij.add(vRight.scale(Xj).add(vUp.scale(Yi)));

    // Return the constructed ray from the camera's location to the calculated point on the view plane
    return new Ray(location, Pij.subtract(location));
}

public RayTraceBase getRayTracer() {
	return rayTracer;
}
public Camera setRayTracer(RayTraceBase r) {
	this.rayTracer = r;
	return this; 
}

public ImageWriter getImageWriter() {
	return imageWriter;
}
public Camera setImageWriter(ImageWriter imageWriter) {
	this.imageWriter = imageWriter;
	return this; 
}
private Color castRay(int nX, int nY, int row, int column) {
    // construct a ray for each pixel though its center 
    Ray ray = this.constructRay(nX, nY, column, row);
    // calculate the color
    Color color = this.rayTracer.traceRay(ray);
    return color;
}
/*

Sets the size of the camera's view plane.
@param w: the width of the view plane
@param h: the height of the view plane
@return the Camera object with the updated view plane size
*/
public Camera setViewPlaneSize(double w, double h) {
    this.width = w;
    this.height = h;
    return this;
}
/*

Sets the distance from the camera to the view plane.
@param d: the distance from the camera to the view plane
*/
public Camera setViewPlaneDistance(double d) {
    distance = d;
    return this;
    
}

/*
*implement the method renderImage to loop over all the ViewPlane’s pixels. 
*For each pixel it will construct a ray using the castRay method.

**/
public Camera renderImage() {
	//Camera camera1=this; 
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
                Ray ray = this.constructRay(nX, nY, j, i); //CAMERA1 is NULL???? 
                Color pixelColor = rayTracer.traceRay(ray); //uses rayTracer
                imageWriter.writePixel(j, i, pixelColor);
            }
        }
    } catch (MissingResourceException e) {
        throw new UnsupportedOperationException("Not implemented yet" + e.getClassName());
 }
    return this; 
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
public Camera writeToImage() {
	 if (imageWriter == null) {
         throw new MissingResourceException("ImageWriter field cannot be null", Camera.class.getName(), "");
     }
     // delegates the appropriate method of the ImageWriter.
     imageWriter.writeToImage();
     return this;
}
private final Camera cameraBuilder(Point l, Vector t, Vector u) {
    this.location = l;
    if (!isZero(t.dotProduct(u))) {
        throw new IllegalArgumentException("Vectors are not orthogonal");
    }

    this.vTo = t.normalize();
    this.vUp = u.normalize();
    this.vRight = this.vTo.crossProduct(this.vUp);
    return this; 
}

}; 

/**
REMOVE BUILDER CLASS PER JOYCE 

public static class Builder{
	private final Camera camera; 
	public Builder(Camera copy) {
		this.camera=copy; 
}
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
    public static Builder getBuilder() {
return new Builder(); 
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
   