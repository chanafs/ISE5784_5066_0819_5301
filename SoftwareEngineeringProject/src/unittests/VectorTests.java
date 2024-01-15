/**
 * 
 */
package unittests;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

import org.junit.jupiter.api.Test;

import primitives.*; 

; /**
 * 
 */
class VectorTests {

	Point  p1         = new Point(1, 2, 3);
    Point  p2         = new Point(2, 4, 6);
    Point  p3         = new Point(2, 4, 5);

    Vector v1         = new Vector(1, 2, 3);
    Vector v1Opposite = new Vector(-1, -2, -3);
    Vector v2         = new Vector(-2, -4, -6);
    Vector v3         = new Vector(0, 3, -2);
    Vector v4         = new Vector(1, 2, 2);
	   
	/**
	 * Test method for {@link primitives.Vector#subtract(primitives.Point)}.
	 */
	@Test
	void testSubtract() {
		assertEquals(new Vector(1, 1, 1),new Point(2, 3, 4).subtract(p1),"ERROR: Point - Point does not work correctly");
	}
	
	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	@Test
	void testAddVector() {
		assertEquals((p1.add(new Vector(-1, -2, -3))),new Point(0, 0, 0),"ERROR: Point + Vector does not work correctly");
	} 
	/**
	 * Test method for {@link primitives.Vector#lengthSquared()}.
	 */
	
	@Test
	void testLengthSquared() {
		assertEquals(14.0001,v1.lengthSquared(),0.0001,"ERROR: lengthSquared() wrong value");

	
	}
	
	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@Test
	void testLength() {
		assertEquals(5.0001,new Vector(0, 3, 4).length(),0.0001,"ERROR: length() wrong value");        
	}

	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	@Test
	void testDotProduct() {
		//tests that the dot product of orthogonal vectors is equal to zero 
		assertEquals(v1.dotProduct(v3),0.0001,0.0001,"ERROR: dotProduct() for orthogonal vectors is not zero");
        //checks the dot product between two vectors 
		assertEquals(-28.0001,v1.dotProduct(v2),0.0001,"ERROR: dotProduct() wrong value");
	}
	
	/**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @Test
    public void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals( v1.length() * v2.length(), vr.length(), 0.00001,"crossProduct() wrong result length");

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue( isZero(vr.dotProduct(v1)),"crossProduct() result is not orthogonal to 1st operand");
        assertTrue( isZero(vr.dotProduct(v2)),"crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-productof co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3),
                "crossProduct() for parallel vectors does not throw an exception");

    }

	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
    @Test
    void testNormalize() {
    	//tests if the normalized vector is a unit vector 
        assertEquals(1.0001,new Vector(1, 2, 3).normalize().length(),0.0001,"ERROR: the normalized vector is not a unit vector");
        //checks if normalized vector is opposite to the original one
        assertFalse(v1.dotProduct(v1.normalize())<0,"ERROR: the normalized vector is opposite to the original one");
        try {
            v1.crossProduct(v1.normalize());
        } catch (Exception e) {
            assertTrue(true);
        }

        
    }	
	/**
	 * Test method for {@link primitives.Vector#scale(double)}.
	 */
	@Test
	void testScale() {
		fail("Not yet implemented");
	}
}