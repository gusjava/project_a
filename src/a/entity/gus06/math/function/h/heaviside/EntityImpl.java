package a.entity.gus06.math.function.h.heaviside;

import a.framework.*;

public class EntityImpl implements Entity, H {

	public String creationDate() {return "20170930";}
	
	public double h(double value) throws Exception
	{
		return value>=0 ? 1 : 0;
	}
}
