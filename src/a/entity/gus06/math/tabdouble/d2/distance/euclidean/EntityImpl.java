package a.entity.gus06.math.tabdouble.d2.distance.euclidean;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170108";}



	public Object t(Object obj) throws Exception
	{
		Object[] t = (Object[]) obj;
		if(t.length!=2) throw new Exception("wrong data number: "+t.length);
		
		double[] f1 = (double[]) t[0];
		double[] f2 = (double[]) t[1];
		
		if(f1.length!=2) throw new Exception("Invalid size for f1: "+f1.length);
		if(f2.length!=2) throw new Exception("Invalid size for f2: "+f2.length);
		
		double dx = f1[0]-f2[0];
		double dy = f1[1]-f2[1];
		
		return Math.sqrt(dx*dx + dy*dy);
	}
}