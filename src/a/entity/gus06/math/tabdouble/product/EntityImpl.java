package a.entity.gus06.math.tabdouble.product;

import a.framework.*;
import java.math.BigDecimal;
import java.math.MathContext;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151030";}

	
	public Object t(Object obj) throws Exception
	{
		double[] dd = (double[]) obj;
		double product = 1;
		for(double d:dd) product *= d;
		return Double.valueOf(product);
	}
}
