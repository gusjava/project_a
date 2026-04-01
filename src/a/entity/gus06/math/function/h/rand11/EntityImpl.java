package a.entity.gus06.math.function.h.rand11;

import a.framework.*;

public class EntityImpl implements Entity, H {

	public String creationDate() {return "20171003";}
	
	public double h(double value) throws Exception
	{return Math.random()*2-1;}
}
