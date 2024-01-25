package geometries;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Plane implements Geometry { 

	private Point q; 
	private Vector normal; 
	/*
	 * constructor*/
	public Plane(Point q, Vector normal) {
		super();//needs?
		this.q = q;
		this.normal = normal;
	}
	/*
	 * constructor*/
	public Plane(Point a, Point b, Point c) {
		super();
		//FOR NOW: 
		this.q = a; //arbitrary
		this.normal = null ;
	}
	public Vector getNormal() {
		return this.normal; 
	}
	public Vector getNormal(Point p) {
		return this.normal; 
	}
	@Override
    public List<Point> findIntersections(Ray ray) {
        Point p0 = ray.getHead();
        Vector v = ray.getDirection();
        Vector n = normal;
        if(q.equals(p0)){
            return null;
        }

        double nv = alignZero(n.dotProduct(v));
        //ray is lying in the play axis
        if (isZero(nv)){
            return null;
        }
        Vector Q0P0 = q.subtract(p0);
        //numerator
        double nQMinusP0 = alignZero(n.dotProduct(Q0P0));
        //t should > 0
        if(isZero(nQMinusP0)){
            return null;
        }
        double t = alignZero(nQMinusP0/nv);
        if(t<= 0){
            return  null;
        }
        return List.of(ray.getPoint(t));
    }

	}
}
