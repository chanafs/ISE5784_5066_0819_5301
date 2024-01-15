/**
 * 
 */
package unittests;
import static org.junit.jupiter.api.Assertions.*;
import primitives.*; 

import org.junit.jupiter.api.Test;

import geometries.Plane;

/**
 * 
 */
class PlaneTests {
	   private final double DELTA = 0.000001;
	   
	   @Test
	    public void testConstructor(){
	        // ============ Equivalence Partitions Tests ==============

	        // TC01: tests the correct construction of plane
	        try {
	            new Plane(new Point(0, 0, 1),new Point(1, 0, 0), new Point(0, 1, 0));      
	        } catch (IllegalArgumentException e) { 
	            fail("Failed constructing a correct plane");
	        }
	        // =============== Boundary Values Tests ==================

	        // TC11: The first and second points are the same
	        assertThrows(IllegalArgumentException.class, 
	                () -> new Plane(new Point(0, 0, 1),new Point(0, 0, 1),new Point(0, 1, 0)),"Constructed a Plane with the same first and second points ");
	        // TC12: The points are on the same line
	        assertThrows(IllegalArgumentException.class, //
	                () -> new Plane(new Point(5, 2, 4),new Point(1, 1, 1),new Point(9, 3, 7)),  "Constructed a plane with the points are on the same line");
	    }
	   
	/**
	 * Test method for {@link geometries.Plane#getNormal()}.
	 */
	   @Test
		void testGetNormal() {
		    Point p1 =new Point(0, 0, 1);
		    Point p2= new Point(1, 0, 0);
		    Point p3 = new Point(0, 1, 0);
			Plane p = new Plane(p1, p2, p3);
			
			  //test that getNormal doesn't throw error
		     assertDoesNotThrow(() -> p.getNormal(new Point(0, 0, 1)), "");
		      // generate the test result
		      Vector result = p.getNormal(new Point(0, 0, 1));
		      // ensure length of result = 1
		      assertEquals(1, result.length(), DELTA, "Plane's normal is not a unit vector");
		      // ensure the result is orthogonal to all the edges
		      assertEquals(0d, result.dotProduct(p2.subtract(p1)), DELTA,
		                      "Plane's normal is not orthogonal to one of the edges");
		      assertEquals(0d, result.dotProduct(p3.subtract(p1)), DELTA,
	                  "Plane's normal is not orthogonal to one of the edges");
		}

	   }
	
