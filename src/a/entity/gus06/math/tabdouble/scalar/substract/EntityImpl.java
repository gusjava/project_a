package a.entity.gus06.math.tabdouble.scalar.substract;

import a.framework.*;
import java.math.BigDecimal;
import java.math.MathContext;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180503";}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		double[] dd = (double[]) o[0];
		double scalar = Double.parseDouble(""+o[1]);
		
		int nb = dd.length;
		double[] r = new double[nb];
		for(int i=0;i<nb;i++) r[i] = dd[i] - scalar;
		return r;
	}
}
