package a.entity.gus06.math.function.solve.polynomial1;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160722";}


	private Service findDoubleArray;


	public EntityImpl() throws Exception
	{
		findDoubleArray = Outside.service(this,"gus06.find.doublearray");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		double[] d = (double[]) findDoubleArray.t(obj);
		
		if(d.length!=2) throw new Exception("Invalid double array for polynomial function of degree 1: "+d.length);
		if(d[1]==0) throw new Exception("Invalid double array for polynomial function of degree 1: last coeff is zero");
		
		double a = d[1];
		double b = d[0];
		
		double x = -b/a;
		return new double[]{x};
	}
}
