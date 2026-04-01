package a.entity.gus06.math.function.trigo.sine;

import a.framework.*;

public class EntityImpl implements Entity, H {

	public String creationDate() {return "20150309";}
	
	public double h(double value) throws Exception
	{return Math.sin(value);}
}
