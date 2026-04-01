package a.entity.gus06.math.function.trigo.tangent;

import a.framework.*;

public class EntityImpl implements Entity, H {

	public String creationDate() {return "20160722";}
	
	public double h(double value) throws Exception
	{return Math.tan(value);}
}
