package geometries; 
import primitives.*; 
//import primitives.Ray; 
/*
 * ADD FINAL TO e/t?? 
 * Several geometric bodies were chosen for implementation in the project (obviously the project can be
expanded to many more bodies). All the departments of these bodies:
• All of them will be immutable, that is to say - all the fields will have a reserved word final
• Access permissions of all fields: in inherited classes - protected, and in non-inherited classes - private.
• We will add constructors as detailed below (it is forbidden to define a default constructor and a copy
constructor]
• We will not define getter methods for the fields unless it is explicitly stated in a certain class that retrieval
methods must be added
• We will not define (forbidden) update methods (setters)
• We will not override the toString method (you can override it if you want)
• The equals method must not be overridden
• It is mandatory to create documentation in javadoc format before each class and before each member of
the class that does not have private permission, with the exception of overriding methods (override)
o The documentation in javadoc format is external documentation and must not refer to the
implementation details of the class/method
o Don't write documentation on overriding (@Override) methods and implementing methods of an
interface
 * 
 * 
 * */

public interface Geometry {
	public Vector getNormal(Point p); 


}
