package a.entity.gus06.math.tabdouble.product.mathcontext.unlimited;

import a.framework.*;
import java.math.BigDecimal;
import java.math.MathContext;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160711";}
	
	public static final MathContext CONTEXT = MathContext.UNLIMITED;

	
	public Object t(Object obj) throws Exception
	{
		double[] dd = (double[]) obj;
		BigDecimal product = new BigDecimal(1, CONTEXT);
		for(double d:dd) product = product.multiply(new BigDecimal(d, CONTEXT), CONTEXT);
		return Double.valueOf(product.doubleValue());
	}
}