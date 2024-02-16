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
   /** Camera builder of the tests */
   /*
   private final Camera camera = new Camera()
      .setRayTracer(new SimpleRayTracer(scene))
      .setLocation(Point.ZERO).setDirection(negOnez, y)
      .setViewPlaneDistance(100)
      .setViewPlaneSize(500, 500);
    */
   
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

   // For stage 6 - please disregard in stage 5
   /**
    * Produce a scene with basic 3D model - including individual lights of the
    * bodies and render it into a png image with a grid
    */
   

   /*
   @Test
   public void renderMultiColorTest() {
      scene.geometries.add( // center
                           new Sphere(50, new Point(0, 0, -100)),
                           // up left
                           new Triangle(new Point(-100, 0, -100), new Point(0, 100, -100), new Point(-100, 100, -100))
                              .setEmission(new Color(GREEN)),
                           // down left
                           new Triangle(new Point(-100, 0, -100), new Point(0, -100, -100), new Point(-100, -100, -100))
                              .setEmission(new Color(RED)),
                           // down right
                           new Triangle(new Point(100, 0, -100), new Point(0, -100, -100), new Point(100, -100, -100))
                              .setEmission(new Color(BLUE)));
      scene.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.2, 0.2, 0.2))); //

      camera
         .setImageWriter(new ImageWriter("color render test", 1000, 1000))
         .renderImage()
         .printGrid(100, new Color(WHITE))
         .writeToImage();
   }
   */
}