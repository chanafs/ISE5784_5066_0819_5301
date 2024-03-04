package geometries;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.isZero;
import static primitives.Util.alignZero; 
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Polygon class represents two-dimensional polygon in 3D Cartesian coordinate
 * system
 * @author Dan
 */
public class Polygon extends Geometry { 
   /** List of polygon's vertices */
   protected final List<Point> vertices;
   /** Associated plane in which the polygon lays */
   protected final Plane       plane;
   /** The size of the polygon - the amount of the vertices in the polygon */
   private final int           size;

   /**
    * Polygon constructor based on vertices list. The list must be ordered by edge
    * path. The polygon must be convex.
    * @param  vertices                 list of vertices according to their order by
    *                                  edge path
    * @throws IllegalArgumentException in any case of illegal combination of
    *                                  vertices:
    *                                  <ul>
    *                                  <li>Less than 3 vertices</li>
    *                                  <li>Consequent vertices are in the same
    *                                  point
    *                                  <li>The vertices are not in the same
    *                                  plane</li>
    *                                  <li>The order of vertices is not according
    *                                  to edge path</li>
    *                                  <li>Three consequent vertices lay in the
    *                                  same line (180&#176; angle between two
    *                                  consequent edges)
    *                                  <li>The polygon is concave (not convex)</li>
    *                                  </ul>
    */
   public Polygon(Point... vertices) {
      if (vertices.length < 3)
         throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
      this.vertices = List.of(vertices);
      size          = vertices.length;
      plane         = new Plane(vertices[0], vertices[1], vertices[2]);
      if (size == 3) return; // no need for more tests for a Triangle

      Vector  n        = plane.getNormal();
      Vector  edge1    = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
      Vector  edge2    = vertices[0].subtract(vertices[vertices.length - 1]);
      boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
      for (var i = 1; i < vertices.length; ++i) {
         // Test that the point is in the same plane as calculated originally
         if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
            throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
         // Test the consequent edges have
         edge1 = edge2;
         edge2 = vertices[i].subtract(vertices[i - 1]);
         if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
            throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
      }
   }

   /**
    * Finds the intersections between the polygon and a given ray.
    * Overrides the method in the Geometry class.
    * @param ray The ray to intersect with the polygon.
    * @return A list of GeoPoints representing the intersections, or null if there are no intersections.
    */
   @Override
   public List<GeoPoint> findGeoIntersections(Ray ray) {
       // find the intersections points of the plane field of the polygon class
       List<GeoPoint> result = plane.findGeoIntersections(ray);
       if (result == null) {
           return null;
       }
       return result;
   }

   /**
   * Helper method for finding the intersections between the polygon and a given ray.
   * Overrides the method in the Geometry class.
   * @param ray The ray to intersect with the polygon.
   * @return A list of GeoPoints representing the intersections, or null if there are no intersections.
   */
	@Override
	public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
		var points = this.plane.findGeoIntersections(ray);
		// Only check point if the ray intersects the plane of the polygon.
		if (points == null)
			return null;
		Point p0 = ray.getHead();
		Vector v = ray.getDirection();
		List<Vector> vectors = new LinkedList<>();
		// Construct vectors to the vertices
		for (Point p : this.vertices) {
			vectors.add(p.subtract(p0));
		}
		int vSize = vectors.size();
		// Cross product each adjacent pair of vectors and check they all share the same
		// sign
		double normal = alignZero(vectors.get(vSize - 1).crossProduct(vectors.get(0)).dotProduct(v));
		if (isZero(normal))
			return null;
		boolean sign = normal > 0;
		for (int i = 0; i < vSize - 1; i++) {
			normal = alignZero(vectors.get(i).crossProduct(vectors.get(i + 1)).dotProduct(v));
			if ((normal > 0) ^ sign || isZero(normal))
				return null;
		}
		points.get(0).geometry = this;
		return points;
	}
   
   @Override
   public Vector getNormal(Point point) { return plane.getNormal(); }

}
