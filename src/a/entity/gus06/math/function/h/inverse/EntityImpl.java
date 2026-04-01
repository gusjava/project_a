package a.entity.gus06.math.function.h.inverse;

import a.framework.*;

public class EntityImpl implements Entity, H {

	public String creationDate() {return "20151209";}
	
	public double h(double value) throws Exception
	{return 1 / value;}
}
