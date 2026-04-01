package a.entity.gus06.math.equation2.resolve;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231101";}


	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof double[])
		{
			double[] coef = (double[]) obj;
			if(coef.length!=3) throw new Exception("Invalid coef number: "+coef.length);
			if(coef[0]==0) throw new Exception("Invalid null first coef");
			
			double a = coef[0];
			double b = coef[1];
			double c = coef[2];
			
			double dis = b*b-4*a*c;
			
			if(dis<0) return new double[]{};
			if(dis==0) return solution0(a,b);
			return solution12(a,b,dis);
		}
		if(obj instanceof int[])
		{
			int[] coef = (int[]) obj;
			if(coef.length!=3) throw new Exception("Invalid coef number: "+coef.length);
			if(coef[0]==0) throw new Exception("Invalid null first coef");
			
			int a = coef[0];
			int b = coef[1];
			int c = coef[2];
			
			double dis = b*b-4*a*c;
			
			if(dis<0) return new double[]{};
			if(dis==0) return solution0((double)a,(double)b);
			return solution12((double)a,(double)b,dis);
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private double[] solution0(double a, double b)
	{
		double c0 = -b/(2*a);
		return new double[]{c0};
	}
	
	private double[] solution12(double a, double b, double dis)
	{
		double dis2 = Math.sqrt(dis);
		double c1 = (-b+dis2)/(2*a);
		double c2 = (-b-dis2)/(2*a);
		return new double[]{c1,c2};
	}
}