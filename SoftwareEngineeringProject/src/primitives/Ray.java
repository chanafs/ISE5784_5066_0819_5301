package primitives;

import java.util.List;

import geometries.Intersectable.GeoPoint; 
import static primitives.Util.isZero;
public class Ray {
	/*
	 * head is the starting point of the ray, direction is the direction vector 
	 */
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
     * Creates a new Ray with the specified starting point and direction vector.
     * The direction vector is normalized to have a length of 1.
     * @param h: The starting point of the ray.
     * @param v: The direction vector of the ray.
     */
	
	public Ray(Point h, Vector v) {
		Vector vN= v.normalize(); 
		this.head = h;
		this.direction = vN;
	}
	/*
     * Returns a string representation of this ray.
     */
	@Override
	public String toString() {
		return "Ray []";
	}
	/*
	 * getPoint calculates point on the ray: 𝑷 = 𝑷𝟎 + 𝒕∙𝒗
	 */
	public Point getPoint(double t) {
		  if (isZero(t)) {
	            return head;
	        }
	        return this.head.add(direction.scale(t)); 

	    }
	 /*
     * Finds the closest point from a list of points.
     * @param pointsList: The list of points to search.
     */
	public Point findClosestPoint(List<Point> points) {
		return points == null || points.isEmpty() ? null
		 : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
		}
    /**
     * Finds the closest GeoPoint from a list of GeoPoints.
     *
     * @param geoPointList The list of GeoPoints to search.
     * @return The closest GeoPoint.
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> geoPointList) {
        if (geoPointList.isEmpty()) {
            return null;
        }
        double shortestDistance = this.head.distance(geoPointList.get(0).point);
        double tempDistance = 0;
        GeoPoint closest = geoPointList.get(0);

        for (GeoPoint geo : geoPointList) {
            tempDistance = this.head.distance(geo.point);
            if (tempDistance < shortestDistance) {
                shortestDistance = tempDistance;
                closest = geo;
            }
        }

        return new GeoPoint(closest.geometry, closest.point);
    }

}
