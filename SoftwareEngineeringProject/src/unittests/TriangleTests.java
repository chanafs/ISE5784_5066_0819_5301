/**
 * 
 */
package unittests;

import static org.junit.jupiter.api.Assertions.*;

import primitives.Point; 
import org.junit.jupiter.api.Test;
import geometries.Plane;
import primitives.Vector;
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


	}

