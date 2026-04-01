package a.entity.gus06.math.function.hyperbolic.cosine;

import a.framework.*;

public class EntityImpl implements Entity, H {

	public String creationDate() {return "20150309";}
	
	public double h(double value) throws Exception
	{return (e(value)+e(-value)) / 2.0;}
	
	private double e(double v)
	{return Math.exp(v);}
}
