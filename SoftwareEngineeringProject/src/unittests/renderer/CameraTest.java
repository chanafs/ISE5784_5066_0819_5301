package unittests.renderer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import primitives.*;
import renderer.*;

/**
 * Testing Camera Class
 * @author Dan
 */
class CameraTest {
	final Point ZERO_POINT = new Point(0, 0, 0);
   @Test
   void testConstructRay() {
	
	   //change second vector to 010 
	   Camera camera = new Camera(ZERO_POINT, new Vector(0, 0, -1), new Vector(0, 0, -1))
				.setViewPlaneDistance(10);
      final String badRay  = "Bad ray";

      // ============ Equivalence Partitions Tests ==============
      // EP01: 4X4 Inside (1,1)
      assertEquals(new Ray(ZERO_POINT, new Vector(1, -1, -10)),
				camera.setViewPlaneSize(8, 8).constructRay(4, 4, 1, 1), badRay);

      // =============== Boundary Values Tests ==================
      // BV01: 4X4 Corner (0,0)
      assertEquals(new Ray(ZERO_POINT, new Vector(3, -3, -10)),
				camera.setViewPlaneSize(8, 8).constructRay(4, 4, 0, 0), badRay);

      // BV02: 4X4 Side (0,1)
      assertEquals(new Ray(ZERO_POINT, new Vector(1, -3, -10)),
				camera.setViewPlaneSize(8, 8).constructRay(4, 4, 1, 0), badRay);

      // BV03: 3X3 Center (1,1)
      assertEquals(new Ray(ZERO_POINT, new Vector(0, 0, -10)),
				camera.setViewPlaneSize(6, 6).constructRay(3, 3, 1, 1), badRay);

      // BV04: 3X3 Center of Upper Side (0,1)
      assertEquals(new Ray(ZERO_POINT, new Vector(0, -2, -10)),
				camera.setViewPlaneSize(6, 6).constructRay(3, 3, 1, 0), badRay);

      // BV05: 3X3 Center of Left Side (1,0)
      assertEquals(new Ray(ZERO_POINT, new Vector(2, 0, -10)),
				camera.setViewPlaneSize(6, 6).constructRay(3, 3, 0, 1), badRay);

      // BV06: 3X3 Corner (0,0)
      assertEquals(new Ray(ZERO_POINT, new Vector(2, -2, -10)),
				camera.setViewPlaneSize(6, 6).constructRay(3, 3, 0, 0), badRay);

   }

}
