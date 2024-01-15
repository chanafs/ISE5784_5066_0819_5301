/**
 * 
 */
package unittests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import primitives.*;
import geometries.*;


/**
 * 
 */
class TubeTests {

	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormal() {
		Ray ray = new Ray(new Point(0,0,0), new Vector(1,0,0));
        Tube tube = new Tube(5, ray);

        // ====== Equivalence Partition Test ======
        Vector expected = new Vector(0,1,0);
        Vector actual = tube.getNormal(new Point(5,5,0));
        assertEquals(expected, actual, "Tube normal EP test failed"); //tests the normal vector at a point on the surface of the tube 

        // ====== Boundary Value Analysis Test ======
        Vector expected_BVA = new Vector(0,1,0);
        Vector actual_BVA = tube.getNormal(new Point(5,5,0));
        assertEquals(expected_BVA, actual_BVA, "Tube normal BVA test failed"); //tests the normal vector at a point on the surface of the tube 

	}

}
