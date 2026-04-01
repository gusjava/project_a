package a.entity.gus06.math.function.probability.poissonlaw;

import java.util.Random;
import a.framework.*;

public class EntityImpl implements Entity, H {

	public String creationDate() {return "20151209";}

	/*
	 * Trouver sur internet ...
	 * http://java.developpez.com/sources/?page=nombresDatesString#LoiExponentielle
	 * A v�rifier !
	 */
	public double h(double value) throws Exception
	{
		Random rand = new Random(); 
		return (-1/value) * Math.log(1-rand.nextDouble()); 
	}
}