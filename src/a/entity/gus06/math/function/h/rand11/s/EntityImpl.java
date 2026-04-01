package a.entity.gus06.math.function.h.rand11.s;

import a.framework.*;

public class EntityImpl implements Entity, H {

	public String creationDate() {return "20171004";}
	
	public double h(double value) throws Exception
	{return Math.random()<0.5 ? -1 : 1;}
}
