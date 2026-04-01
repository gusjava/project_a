package a.entity.gus06.convert.doublearraytoobjarray;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160626";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		double[] oo = (double[]) obj;
		int number = oo.length;
		
		Double[] yy = new Double[number];
		for(int i=0;i<number;i++) yy[i] = Double.valueOf(oo[i]);
		return yy;
	}
}
