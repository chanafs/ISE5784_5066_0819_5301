package geometries;
import java.util.List;
import primitives.Vector; 
import primitives.Point;
import primitives.Ray;

public class Triangle extends Polygon{
	public Triangle(Point... vertices) { //3 Points
		super(vertices);
	}
@Override 
public List<Point> findIntersections(Ray ray)
        {
                List<Point> List = plane.findIntersections(ray);
                if(List == null)
                {
                        return null;
                }
                Point test = List.get(0);
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
}