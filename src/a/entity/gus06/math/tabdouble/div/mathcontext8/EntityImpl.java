package a.entity.gus06.math.tabdouble.div.mathcontext8;

import a.framework.*;
import java.math.BigDecimal;
import java.math.MathContext;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231020";}
	
	public static final MathContext CONTEXT = new MathContext(8);

	
	public Object t(Object obj) throws Exception
	{
		double[] dd = (double[]) obj;
		if(dd.length!=2) throw new Exception("Invalid data number: "+dd.length);
		
		BigDecimal d1 = new BigDecimal(dd[0], CONTEXT);
		BigDecimal d2 = new BigDecimal(dd[1], CONTEXT);
		BigDecimal r = d1.divide(d2, CONTEXT);
		
		return Double.valueOf(r.doubleValue());
	}
}