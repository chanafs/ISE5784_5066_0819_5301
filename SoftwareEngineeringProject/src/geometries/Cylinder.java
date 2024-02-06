package geometries;
import primitives.*; 
import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Cylinder extends Tube{
	private double height; 
	/*
	 * constructor
	 *@param radius:The radius of the cylinder base
     *@param axisRay: The axis ray of the cylinder.
     *@param height: The height of the cylinder.
	 */
	public Cylinder(double radius, Ray axisRay, double height) {
		super(radius, axisRay);
		this.height = height;
	}
	/*
    *getHeight returns the height of the cylinder
    */
   public double getHeight() {
       return height;
   }
   /*
    *getNormal returns the normal of the cylinder
    *@param point
    */
   @Override
   public Vector getNormal(Point point) {
       Point p0 = axis.getHead();
       Vector v = axis.getDirection();
       
       //returns v because it is in the direction of the axis 
       if (point.equals(p0))
           return v;
       
       //project point-p0 on the ray 
       Vector u = point.subtract(p0);

       // distance from p0 to p1
       double t = alignZero(u.dotProduct(v));

       //if the given point is at the base of the cylinder, return direction vector 
       if (t == 0 || isZero(height - t))
           return v;
       
       //Calculates the other point on the axis facing the given point
       Point p1= p0.add(v.scale(t));
       
       //return the normalized vector 
       return point.subtract(p1).normalize();
   }
		
}
