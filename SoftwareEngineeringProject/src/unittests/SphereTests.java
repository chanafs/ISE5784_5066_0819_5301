/**
 * 
 */
package unittests;
import primitives.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

import org.junit.jupiter.api.Test;

import geometries.*;

/**
 * 
 */
class SphereTests {
	private final double DELTA = 0.000001;
	/**
	 * Test method for {@link geometries.Sphere#getNormal(primitives.Point)}.
	 */
	@Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct construct Sphere
        try {
        	Point p =  new Point(0, 0, 1); 
            new Sphere(5.0, p); //sending 5.0 to be the radius, p to the center Point 
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct sphere");
        }
    }
	 @Test
	    void testGetNormal() {
		 Sphere s = new Sphere(1, Point.ZERO);
			//test that getNormal for sphere does not throw an exception
			assertDoesNotThrow(()-> s.getNormal(new Point(1,0,0)),"");
			Vector result = s.getNormal(new Point(1,0,0));
			//check length of result = 1
			assertEquals(1,result.length(), DELTA , "Sphere's vector is not a unit vector");
			
	    }
	  @Test
	    void testFindIntersections() {
	        Sphere sphere = new Sphere( 1d,new Point(1, 0, 0));

	        // ============ Equivalence Partitions Tests ==============
	        Point gp1 = new Point(0.0651530771650466, 0.355051025721682, 0);
	        Point gp2 = new Point(1.53484692283495, 0.844948974278318, 0);
	        List<Point> exp = List.of(gp1, gp2);
	        // TC01: Ray's line is outside the sphere (0 points)
	        assertNull(sphere.findIntersections(
	                new Ray(
	                        new Point(-1, 0, 0),
	                        new Vector(1, 1, 0))),
	                "Ray's line out of sphere");
	        // TC02: Ray starts before and crosses the sphere (2 points)
	        List<Point> result = sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(3, 1, 0)));
	        assertEquals(2, result.size(), "Wrong number of points");
	        if (result.get(0).getxyz().getD1() > result.get(1).getxyz().getD1())
	            result = List.of(result.get(1), result.get(0));
	        assertEquals(exp, result, "Ray crosses sphere");
	        // TC03: Ray starts inside the sphere (1 point)
	        assertEquals(
	                List.of(gp2),
	                sphere.findIntersections(
	                        new Ray(
	                                new Point(0.5, 0.5, 0),
	                                new Vector(3, 1, 0))),
	                "Ray from inside sphere");
	        // TC04: Ray starts after the sphere (0 points)
	        assertNull(
	                sphere.findIntersections(
	                        new Ray(new Point(2, 1, 0), new Vector(3, 1, 0))),
	                "Sphere behind Ray");
	        // =============== Boundary Values Tests ==================
	        // **** Group: Ray's line crosses the sphere (but not the center)
	        // TC11: Ray starts at sphere and goes inside (1 points)
	        assertEquals(List.of(new Point(2, 0, 0)),
	                sphere.findIntersections(new Ray(new Point(1, -1, 0), new Vector(1, 1, 0))),
	                "Ray from sphere inside");
	        // TC12: Ray starts at sphere and goes outside (0 points)
	        assertNull(
	                sphere.findIntersections(new Ray(new Point(2, 0, 0), new Vector(1, 1, 0))),
	                "Ray from sphere outside");

	        // **** Group: Ray's line goes through the center
	        // TC13: Ray starts before the sphere (2 points)
	        result = sphere.findIntersections(new Ray(new Point(1, -2, 0), new Vector(0, 1, 0)));
	        assertEquals(2, result.size(), "Wrong number of points");
	        if (result.get(0).getxyz().getD2() > result.get(1).getxyz().getD2())
	            result = List.of(result.get(1), result.get(0));
	        assertEquals(List.of(new Point(1, -1, 0), new Point(1, 1, 0)),
	                result,
	                "Line through O, ray crosses sphere");
	        // TC14: Ray starts at sphere and goes inside (1 points)
	        assertEquals(List.of(new Point(1, 1, 0)),
	                sphere.findIntersections(new Ray(new Point(1, -1, 0), new Vector(0, 1, 0))),
	                "Line through O, ray from and crosses sphere");
	        // TC15: Ray starts inside (1 points)
	        assertEquals(List.of(new Point(1, 1, 0)),
	                sphere.findIntersections(new Ray(new Point(1, 0.5, 0), new Vector(0, 1, 0))),
	                "Line through O, ray from inside sphere");
	        // TC16: Ray starts at the center (1 points)
	        assertEquals(List.of(new Point(1, 1, 0)),
	                sphere.findIntersections(new Ray(new Point(1, 0, 0), new Vector(0, 1, 0))),
	                "Line through O, ray from O");
	        // TC17: Ray starts at sphere and goes outside (0 points)
	        assertNull(sphere.findIntersections(new Ray(new Point(1, 1, 0), new Vector(0, 1, 0))),
	                "Line through O, ray from sphere outside");
	        // TC18: Ray starts after sphere (0 points)
	        assertNull(sphere.findIntersections(new Ray(new Point(1, 2, 0), new Vector(0, 1, 0))),
	                "Line through O, ray outside sphere");

	        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
	        // TC19: Ray starts before the tangent point
	        assertNull(sphere.findIntersections(new Ray(new Point(0, 1, 0), new Vector(1, 0, 0))),
	                "Tangent line, ray before sphere");
	        // TC20: Ray starts at the tangent point
	        assertNull(sphere.findIntersections(new Ray(new Point(1, 1, 0), new Vector(1, 0, 0))),
	                "Tangent line, ray at sphere");
	        // TC21: Ray starts after the tangent point
	        assertNull(sphere.findIntersections(new Ray(new Point(2, 1, 0), new Vector(1, 0, 0))),
	                "Tangent line, ray after sphere");

	        // **** Group: Special cases
	        // TC22: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
	        assertNull(sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(0, 0, 1))),
	                "Ray orthogonal to ray head -> O line");
	    }
}


