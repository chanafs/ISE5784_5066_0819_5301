package unittests.renderer;

import static java.awt.Color.*;

import org.junit.jupiter.api.Test;

import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import primitives.*;
import renderer.*;
import geometries.*;
import scene.Scene;

/**
 * Test rendering a basic image
 * @author Dan
 */
public class RenderTest2 {
    Vector y =   new Vector (0,1,0);
    Vector negOnez= new Vector (0,0,-1);
   /** Scene of the tests */
   private final Scene          scene  = new Scene("Test scene");
   Camera camera = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0)) //
           .setViewPlaneDistance(100) //
           .setViewPlaneSize(500.0, 500.0) //
           .setImageWriter(new ImageWriter("base render test", 1000, 1000))
           .setRayTracer(new SimpleRayTracer(scene));

   /**
    * Produce a scene with basic 3D model and render it into a png image with a
    * grid
    */
   @Test
   public void renderTwoColorTest() {
      scene.geometries.add(new Sphere(50d, new Point(0, 0, -100)),
                           new Triangle(new Point(-100, 0, -100), new Point(0, 100, -100), new Point(-100, 100, -100)), // up
                           // left
                           new Triangle(new Point(-100, 0, -100), new Point(0, -100, -100),
                                        new Point(-100, -100, -100)), // down
                           // left
                           new Triangle(new Point(100, 0, -100), new Point(0, -100, -100), new Point(100, -100, -100))); // down
      scene.setAmbientLight(new AmbientLight(new Color(255, 191, 191), Double3.ONE))
         .setBackground(new Color(75, 127, 90));

      // right
      camera.setImageWriter(new ImageWriter("base render test", 1000, 1000))
      .renderImage()
      .printGrid(100, new Color(YELLOW))
      .writeToImage();

   }
}
