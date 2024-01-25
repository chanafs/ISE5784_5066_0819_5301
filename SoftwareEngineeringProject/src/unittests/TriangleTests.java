/**
 * 
 */
package unittests;

import static org.junit.jupiter.api.Assertions.*;

import primitives.Point;
import primitives.Ray;

import org.junit.jupiter.api.Test;
import geometries.Plane;
import primitives.Vector;
import java.util.List;
import geometries.Triangle;


/**
 * 
 */
class TriangleTests {
	private final double DELTA = 0.000001;
	/**
	 * Test method for {@link geometries.Polygon#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormal() {
				 Point p1 =new Point(0, 0, 1);
				 Point p2= new Point(1, 0, 0);
				 Point p3 = new Point(0, 1, 0);
				    
				 Triangle t = new Triangle(p1, p2, p3);
					
				//test that getNormal doesn't throw error
			     assertDoesNotThrow(() -> t.getNormal(new Point(0, 0, 1)), "");
				// generate the test result
			     Vector result = t.getNormal(new Point(0, 0, 1));
				//test length of result = 1
				 assertEquals(1, result.length(), DELTA, "Triangles normal is not a unit vector");
				 //test the result is orthogonal to all the edges
				  assertEquals(0d, result.dotProduct(p2.subtract(p1)), DELTA,
				                      "Triangle's normal is not orthogonal to one of the edges");
				  assertEquals(0d, result.dotProduct(p3.subtract(p1)), DELTA,
			                  "Triangle's normal is not orthogonal to one of the edges");	}

	@Test

    void testFindIntersections() {

        Triangle tr = new Triangle(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));

        Plane pl = new Plane(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));

        Ray ray;

        // ============ Equivalence Partitions Tests ==============

        // TC01: Inside triangle

        ray = new Ray(new Point(1, 1, 1), new Vector(-1, -1, -1));

        assertEquals(List.of(new Point(1d / 3, 1d / 3, 1d / 3)), tr.findIntersections(ray),

                "Bad intersection");



        // TC02: Against edge

        ray = new Ray(new Point(0, 0, -1), new Vector(1, 1, 0));

        assertEquals(List.of(new Point(1, 1, -1)), pl.findIntersections(ray),

                "Wrong intersection with plane");

        assertNull(tr.findIntersections(ray), "Bad intersection");



        // TC03: Against vertex

        ray = new Ray(new Point(0, 0, 2), new Vector(-1, -1, 0));

        assertEquals(List.of(new Point(-0.5, -0.5, 2)), pl.findIntersections(ray),

                "Wrong intersection with plane");

        assertNull(tr.findIntersections(ray), "Bad intersection");



        // =============== Boundary Values Tests ==================

        // TC11: In vertex

        ray = new Ray(new Point(-1, 0, 0), new Vector(1, 1, 0));

        assertEquals(List.of(new Point(0, 1, 0)), pl.findIntersections(ray),

                "Wrong intersection with plane");

        assertNull(tr.findIntersections(ray), "Bad intersection");



        // TC12: On edge

        ray = new Ray(new Point(-1, -1, 0), new Vector(1, 1, 0));

        assertEquals(List.of(new Point(0.5, 0.5, 0)), pl.findIntersections(ray),

                "Wrong intersection with plane");

        assertNull(tr.findIntersections(ray), "Bad intersection");



        // TC13: On edge continuation

        ray = new Ray(new Point(-2, 0, 0), new Vector(1, 1, 0));

        assertEquals(List.of(new Point(-0.5, 1.5, 0)), pl.findIntersections(ray),

                "Wrong intersection with plane");

        assertNull(tr.findIntersections(ray), "Bad intersection");

    }

}



