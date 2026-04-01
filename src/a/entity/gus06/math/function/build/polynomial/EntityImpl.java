package a.entity.gus06.math.function.build.polynomial;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151129";}
	

	private Service findDoubleArray;
	
	public EntityImpl() throws Exception
	{
		findDoubleArray = Outside.service(this,"gus06.find.doublearray");
	}

	
	public Object t(Object obj) throws Exception
	{
		double[] d = (double[]) findDoubleArray.t(obj);
		if(d.length==0) throw new Exception("Invalid polynomial coeff number: 0");
		
		return new H1(d);
	}
	
	
	
	private class H1 implements H
	{
		private double[] coef;
		public H1(double[] coef)
		{this.coef = coef;}
		
		public double h(double value)
		{
			int n = coef.length-1;
			double r = coef[n];
			for(int i=n-1;i>=0;i--)
				r = r * value + coef[i];
			return r;
		}
	}
}
