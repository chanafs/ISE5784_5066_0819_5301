/**
 * 
 */
package unittests;
import static org.junit.jupiter.api.Assertions.*;
import primitives.Double3;
import primitives.Point;
import primitives.Ray;
import org.junit.jupiter.api.Test;
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
	    void findIntersections() {
	        Triangle test = new Triangle(new Point(0,0,0),new Point(-2,0,0),new Point(0,0,2));
	        Point testPoint = new Point(0,-2,0);
	        List<Point> result;
	        // ============ Equivalence Partitions Tests ==============
	        
	        // TC01: Inside triangle
	        result = test.findIntersections(new Ray(testPoint,new Vector(new Double3(-0.83,2,0.58))));
	        assertEquals(result.get(0),new Point(-0.83,0,0.58));
	        // TC02: Against edge
	        result = test.findIntersections(new Ray(testPoint,new Vector(new Double3(-2,2,2))));
	        assertNull(result);
	        // TC03: Against vertex
	        result = test.findIntersections(new Ray(testPoint,new Vector(new Double3(0.5,2,0))));
	        assertNull(result);
	        
	        // =============== Boundary Values Tests ==================
	        
	        // TC11: In vertex
	        result = test.findIntersections(new Ray(testPoint,new Vector(new Double3(-1.61,-0.13,0))));
	        assertNull(result);
	        // TC12: On edge
	        result = test.findIntersections(new Ray(testPoint,new Vector(new Double3(-1,2,0))));
	        assertEquals(result.get(0),new Point(-1,0,0));
	        // TC13: On edge continuation
	        result = test.findIntersections(new Ray(testPoint,new Vector(new Double3(-2,2,0))));
	        assertEquals(result.get(0),new Point(-2,0,0));
	    }

}



