package a.entity.gus06.math.function.h.decrement;

import a.framework.*;

public class EntityImpl implements Entity, H {

	public String creationDate() {return "20160809";}
	
	public double h(double value) throws Exception
	{return value-1;}
}
