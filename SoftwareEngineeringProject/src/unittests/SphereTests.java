/**
 * 
 */
package unittests;

import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

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

}


