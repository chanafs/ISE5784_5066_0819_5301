package geometries; 
import java.util.List;

import primitives.*; 

public interface Geometry extends Intersectable{
	public Vector getNormal(Point p); 

	
}
