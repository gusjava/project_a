package a.entity.gus06.math.function.h.signum;

import a.framework.*;

public class EntityImpl implements Entity, H {

	public String creationDate() {return "20170917";}
	
	public double h(double value) throws Exception
	{
		return value>0 ? 1 : value<0 ? -1 : 0;
	}
}
