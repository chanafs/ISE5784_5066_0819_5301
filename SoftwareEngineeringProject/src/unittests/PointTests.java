/**
 * 
 */
package unittests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import primitives.Point;
import primitives.Vector; 

/**
 * 
 */
class PointTests {
    Point  p1         = new Point(1, 2, 3);
    Point  p2         = new Point(2, 4, 6);
    Point  p3         = new Point(2, 4, 5);

    Vector v1         = new Vector(1, 2, 3);
    Vector v1Opposite = new Vector(-1, -2, -3);
    Vector v2         = new Vector(-2, -4, -6);
    Vector v3         = new Vector(0, 3, -2);
    Vector v4         = new Vector(1, 2, 2);

	/**
	 * Test method for {@link primitives.Point#add(primitives.Vector)}.
	 */

	@Test
	void testAdd(Vector v1) {
		//adds a vector to a point, checks if its successful 
	    assertEquals(p1.add(v1), p2, "ERROR: (point + vector) = other point does not work correctly");
	    //adds v1Opposite to a point, checks if (point + vector) = center of coordinates does not work correctly 
	    assertEquals(p1.add(v1Opposite), Point.ZERO, "ERROR: (point + vector) = center of coordinates does not work correctly");
	}

	/**
	 * Test method for {@link primitives.Point#subtract(primitives.Point)}.
	 */
	@Test
	void testSubtract() {
	//subtracting one point from the other isn't successful 
		assertEquals(p2.subtract(p1), v1,"ERROR: (point2 - point1) does not work correctly");
	//subtracting point by itself doesn't throw error
    assertThrows( IllegalArgumentException.class, () -> p1.subtract(p1), "Subtracting point by itself does not throw exception");
    }
	/**
	 * Test method for {@link primitives.Point#distance(primitives.Point)}.
	 */
	@Test
	void testDistance() {
		
	}

	/**
	 * Test method for {@link primitives.Point#distanceSquared(primitives.Point)}.
	 */
	@Test
	void testDistanceSquared() {
	}

}



