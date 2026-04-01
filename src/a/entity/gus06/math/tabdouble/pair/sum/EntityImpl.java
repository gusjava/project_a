package a.entity.gus06.math.tabdouble.pair.sum;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160705";}



	public Object t(Object obj) throws Exception
	{
		Object[] t = (Object[]) obj;
		if(t.length!=2) throw new Exception("wrong data number: "+t.length);
		
		double[] f1 = (double[]) t[0];
		double[] f2 = (double[]) t[1];
		
		if(f1.length!=f2.length)
			throw new Exception("Vectors have not the same size: sum not computed");
		return sum(f1,f2);
	}

	
	private double[] sum(double[] f1, double[] f2)
	{
		int nb = f1.length;
		double[] d = new double[nb];
		for(int i=0;i<nb;i++)
		d[i] = f1[i]+f2[i];
		
		return d;
	}
}