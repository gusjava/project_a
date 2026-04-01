package a.entity.gus06.math.tabdouble.equals;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20180504";}



	public boolean f(Object obj) throws Exception
	{
		Object[] t = (Object[]) obj;
		if(t.length!=2) throw new Exception("wrong data number: "+t.length);
		
		double[] f1 = (double[]) t[0];
		double[] f2 = (double[]) t[1];
		
		int len = f1.length;
		if(len!=f2.length) return false;
		
		for(int i=0;i<len;i++) if(f1[i]!=f2[i]) return false;
		return true;
	}
}