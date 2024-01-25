package geometries;

import java.util.List;
import static primitives.Util.alignZero;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Sphere extends RadialGeometry{
	private Point center; 
	 /*
	  * constructor*/
	public Sphere(double r, Point c) {
		super(r); //sets radius by sending it to constructor of RadialGeometry
		this.setCenter(c);
	}

	public Vector getNormal(Point p) {
		return (p.subtract(center)).normalize();
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}
	@Override
	 public List<Point> findIntersections(Ray ray) {
	        Point P0 = ray.getHead();
	        Vector v = ray.getDirection();

	        if (P0.equals(center)) {
	            return List.of(center.add(v.scale(radius)));
	        }

	        Vector U = center.subtract(P0);

	        double tm = alignZero(v.dotProduct(U));
	        double d = alignZero(Math.sqrt(U.lengthSquared() - tm * tm));

	        // no intersections : the ray direction is above the sphere
	        if (d >= radius) {
	            return null;
	        }

	        double th = alignZero(Math.sqrt(radius * radius - d * d));
	        double t1 = alignZero(tm - th);
	        double t2 = alignZero(tm + th);

	        if (t1 > 0 && t2 > 0) {
//	            Point P1 = P0.add(v.scale(t1));
//	            Point P2 = P0.add(v.scale(t2));
	            Point P1 =ray.getPoint(t1);
	            Point P2 =ray.getPoint(t2);
	            return List.of(P1, P2);
	        }
	        if (t1 > 0) {
//	            Point P1 = P0.add(v.scale(t1));
	            Point P1 =ray.getPoint(t1);
	            return List.of(P1);
	        }
	        if (t2 > 0) {
//	            Point P2 = P0.add(v.scale(t2));
	            Point P2 =ray.getPoint(t2);
	            return List.of(P2);
	        }
	        return null;
	    }
	
}
