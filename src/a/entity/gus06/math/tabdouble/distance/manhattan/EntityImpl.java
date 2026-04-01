package a.entity.gus06.math.tabdouble.distance.manhattan;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151209";}



	public Object t(Object obj) throws Exception
	{
		Object[] t = (Object[]) obj;
		if(t.length!=2) throw new Exception("wrong data number: "+t.length);
		
		double[] f1 = (double[]) t[0];
		double[] f2 = (double[]) t[1];
		
		if(f1.length!=f2.length)
			throw new Exception("Vectors have not the same size: distance not computed");
		return Double.valueOf(distance(f1,f2));
	}

	
	private double distance(double[] f1, double[] f2)
	{
		double d = 0;
		for(int i=0;i<f1.length;i++)
		{
			double r = Math.abs(f1[i]-f2[i]);
			d += r;
		}
		return d;
	}
}