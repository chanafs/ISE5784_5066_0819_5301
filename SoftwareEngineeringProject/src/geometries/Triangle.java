package geometries;
import java.util.List;
import java.util.Vector;

import primitives.Double3;
import primitives.Point;
import primitives.Ray;

public class Triangle extends Polygon{
	//verticies.size()=3; 
	public Triangle(Point... vertices) {
		super(vertices);
	}
/*public List<Point3D> findIntersection(Ray ray)
        {
                List<Point3D> List = plane.findIntersections(ray);
                if(List == null)
                {
                        return null;
                }
                Point3D test = List.get(0);
                Vector v1 = ray.getPoint().subtract(vertices.get(0));
                Vector v2 = ray.getPoint().subtract(vertices.get(1));
                Vector v3 = ray.getPoint().subtract(vertices.get(2));
                Vector n1 = (vertices.get(1).subtract(vertices.get(0))).crossProduct(v1).normalize();
                Vector n2 = (vertices.get(2).subtract(vertices.get(1))).crossProduct(v2).normalize();
                Vector n3 = (vertices.get(0).subtract(vertices.get(2))).crossProduct(v3).normalize();
                double number1 = ray.getVec().dotProduct(n1);
                double number2 = ray.getVec().dotProduct(n2);
                double number3 = ray.getVec().dotProduct(n3);
                if(number1 >= 0 && number2 >= 0 && number3 >= 0)
                {
                        return List;
                }
                if(number1 <= 0 && number2 <= 0 && number3 <= 0) {
                        return List;
                }
                return null;
        }*/
	@Override
  public List<Double3> findIntersection(Ray ray)

  	{

  		List<Double3> List = plane.findIntersections(ray);

  		if(List == null)

  		{

  			return null;

  		}

  		Double3 test = List.get(0);

  		Vector v1 = ray.getHead().subtract(vertices.get(0));

  		Vector v2 = ray.getHead().subtract(vertices.get(1));

  		Vector v3 = ray.getHead().subtract(vertices.get(2));

  		Vector n1 = (vertices.get(1).subtract(vertices.get(0))).crossProduct(v1).normalize();

  		Vector n2 = (vertices.get(2).subtract(vertices.get(1))).crossProduct(v2).normalize();

  		Vector n3 = (vertices.get(0).subtract(vertices.get(2))).crossProduct(v3).normalize();

  		double number1 = ray.getDirection().dotProduct(n1);

  		double number2 = ray.getDirection().dotProduct(n2);

  		double number3 = ray.getDirection().dotProduct(n3);

  		if(number1 >= 0 && number2 >= 0 && number3 >= 0)

  		{

  			return List;

  		}

  		if(number1 <= 0 && number2 <= 0 && number3 <= 0) {

  			return List;

  		}

  		return null;

  	}