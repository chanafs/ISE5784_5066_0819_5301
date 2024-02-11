package geometries;

import java.util.List;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Tube extends RadialGeometry{

	protected Ray axis; 
	/*
     * Constructs a Tube object with the specified radius and axis Ray.
     *
     * @param radius: the radius of the tube
     * @param axis: the axis Ray of the tube
     */
	public Tube(double radius, Ray axis) {
		super(radius);
		this.axis = axis;
	}
	
    /*
     * Returns the normal vector to the tube at the specified point.
     *
     * @param p: a Point on the tube
     */
	public Vector getNormal(Point p) {
        double t = axis.getDirection().dotProduct(p.subtract(axis.getHead()));
        Point o = axis.getHead().add(axis.getDirection().scale(t));
        return p.subtract(o).normalize();
    }
	/*@Override
	public List<Point> findIntersections(Ray ray) {
		return null;
	}*/
	@Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return null;
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        return null;
    }

}
