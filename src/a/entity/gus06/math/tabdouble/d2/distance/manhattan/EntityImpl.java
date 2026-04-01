package a.entity.gus06.math.tabdouble.d2.distance.manhattan;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170108";}



	public Object t(Object obj) throws Exception
	{
		Object[] t = (Object[]) obj;
		if(t.length!=2) throw new Exception("wrong data number: "+t.length);
		
		double[] f1 = (double[]) t[0];
		double[] f2 = (double[]) t[1];
		
		double dx = Math.abs(f1[0]-f2[0]);
		double dy = Math.abs(f1[1]-f2[1]);
		
		return dx+dy;
	}
}