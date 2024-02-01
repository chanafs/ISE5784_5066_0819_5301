/**
 * 
 */
package renderer;

import java.awt.Color;

import primitives.Ray;
import scene.Scene;

/**
 * 
 */
public abstract class RayTraceBase {
protected Scene scene;

RayTraceBase(Scene s) {
    scene = s;
}
/*
 * t receives a ray as a parameter and returns a color
 * */
public abstract Color traceRay(Ray ray);
}