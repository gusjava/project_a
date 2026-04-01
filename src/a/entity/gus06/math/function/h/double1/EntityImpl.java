package a.entity.gus06.math.function.h.double1;

import a.framework.*;

public class EntityImpl implements Entity, H {

	public String creationDate() {return "20160722";}
	
	public double h(double value) throws Exception
	{return value*2;}
}
