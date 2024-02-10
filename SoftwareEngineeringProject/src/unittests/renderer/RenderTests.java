package unittests.renderer;

import static java.awt.Color.*;

import org.junit.jupiter.api.Test;

import geometries.*;
import lighting.AmbientLight;
import primitives.*;
import renderer.*;
import scene.Scene;

/** Test rendering a basic image
 * @author Dan */
public class RenderTests {
   /** Scene of the tests */
 // private final Scene scene  = new Scene("Test scene");
	
   /** Produce a scene with basic 3D model and render it into a png image with a
    * grid */
	
@Test
public void RenderTwoColorTest() {
    Scene scene = new Scene("Test scene")//
            .setAmbientLight(new AmbientLight(new Color(255, 191, 191), new Double3(1, 1, 1))) //
            .setBackground(new Color(75, 127, 90));

    scene.getGeometries().add(new Sphere(50d, new Point(0, 0, -100)),
            new Triangle(new Point(-100, 0, -100), new Point(0, 100, -100), new Point(-100, 100, -100)), // up
            // left
            new Triangle(new Point(-100, 0, -100), new Point(0, -100, -100), new Point(-100, -100, -100)), // down
            // left
            new Triangle(new Point(100, 0, -100), new Point(0, -100, -100), new Point(100, -100, -100))); // down
    // right
    Camera camera = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0));
    camera.setViewPlaneDistance(100);
    camera.setViewPlaneSize(500, 500);
    camera.setImageWriter(new ImageWriter("base render test", 1000, 1000));
    camera.setRayTracer(new SimpleRayTracer(scene));
    camera.renderImage();
    camera.printGrid(100, new Color(YELLOW));
    camera.writeToImage();
}

  /* Test for XML based scene - for bonus 
   @Test
   public void basicRenderXml() {
      // enter XML file name and parse from XML file into scene object
      // using the code you added in appropriate packages
      // ...
      // NB: unit tests is not the correct place to put XML parsing code

      camera
         .setImageWriter(new ImageWriter("xml render test", 1000, 1000))
         .build()
         .renderImage()
         .printGrid(100, new Color(YELLOW))
         .writeToImage();
   } **/
}

