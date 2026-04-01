package a.entity.gus06.math.tabint.pair.product;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160705";}



	public Object t(Object obj) throws Exception
	{
		Object[] t = (Object[]) obj;
		if(t.length!=2) throw new Exception("wrong data number: "+t.length);
		
		int[] f1 = (int[]) t[0];
		int[] f2 = (int[]) t[1];
		
		if(f1.length!=f2.length)
			throw new Exception("Vectors have not the same size: product not computed");
		return Integer.valueOf(product(f1,f2));
	}

	
	private int product(int[] f1, int[] f2)
	{
		int d = 0;
		for(int i=0;i<f1.length;i++)
		d += f1[i]*f2[i];
		
		return d;
	}
}