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
/**
 * Produce a scene with basic 3D model - including individual lights of the
 * bodies and render it into a png image with a grid
 */
@Test
public void renderMultiColorTest() {
    Scene scene = new Scene("Test scene");

    Sphere sphere = new Sphere(50, new Point(0, 0, -100));
    Triangle triangle1 = new Triangle(new Point(-100, 0, -100), new Point(0, 100, -100), new Point(-100, 100, -100));
    Triangle triangle2 = new Triangle(new Point(-100, 0, -100), new Point(0, -100, -100), new Point(-100, -100, -100));
    Triangle triangle3 = new Triangle(new Point(100, 0, -100), new Point(0, -100, -100), new Point(100, -100, -100));

    triangle1.setEmission(new Color(GREEN));
    triangle2.setEmission(new Color(RED));
    triangle3.setEmission(new Color(BLUE));

    scene.geometries.add(sphere, triangle1, triangle2, triangle3);

    scene.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.2, 0.2, 0.2)));

    Camera camera = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0))
            .setViewPlaneDistance(100)
            .setViewPlaneSize(500, 500)
            .setImageWriter(new ImageWriter("color render test", 1000, 1000))
            .setRayTracer(new SimpleRayTracer(scene));

    camera.renderImage();
    camera.printGrid(100, new Color(WHITE)); 
    camera.writeToImage();

  
}
}

