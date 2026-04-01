package a.entity.gus06.math.function.solve.polynomial2;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160722";}
	
	public final double E = 1E-6;


	private Service findDoubleArray;


	public EntityImpl() throws Exception
	{
		findDoubleArray = Outside.service(this,"gus06.find.doublearray");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		double[] d = (double[]) findDoubleArray.t(obj);
		
		if(d.length!=3) throw new Exception("Invalid double array for polynomial function of degree 2: "+d.length);
		if(d[2]==0) throw new Exception("Invalid double array for polynomial function of degree 2: last coeff is zero");
		
		double a = d[2];
		double b = d[1];
		double c = d[0];
		
		double delta = b*b-4*a*c;
		
		if(Math.abs(delta) < E)
		{
			double x = (-b)/(2*a);
			return new double[]{x};
		}
		if(delta>0)
		{
			double delta2 = Math.sqrt(delta);
			double x1 = (-b-delta2)/(2*a);
			double x2 = (-b+delta2)/(2*a);
			return new double[]{x1,x2};
		}
		
		return new double[]{};
	}
}
