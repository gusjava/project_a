package a.entity.gus06.math.tabdouble.sum.abs.mathcontext8;

import a.framework.*;
import java.math.MathContext;
import java.math.BigDecimal;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231020";}
	
	public static final MathContext CONTEXT = new MathContext(8);

	
	public Object t(Object obj) throws Exception
	{
		double[] dd = (double[]) obj;
		BigDecimal sum = new BigDecimal(0, CONTEXT);
		for(double d:dd) sum = sum.add(new BigDecimal(Math.abs(d), CONTEXT), CONTEXT);
		return Double.valueOf(sum.doubleValue());
	}
}