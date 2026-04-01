package a.entity.gus06.math.function.h.tanh;

import a.framework.*;

public class EntityImpl implements Entity, H {

	public String creationDate() {return "20171015";}
	
	public double h(double value) throws Exception
	{return Math.tanh(value);}
}
