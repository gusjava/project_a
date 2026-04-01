package a.entity.gus06.math.function.h.rectifier;

import a.framework.*;

public class EntityImpl implements Entity, H {

	public String creationDate() {return "20171015";}
	
	public double h(double value) throws Exception
	{
		return value>=0 ? value : 0;
	}
}
