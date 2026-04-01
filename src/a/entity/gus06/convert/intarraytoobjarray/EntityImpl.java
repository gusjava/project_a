package a.entity.gus06.convert.intarraytoobjarray;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160626";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		int[] oo = (int[]) obj;
		int number = oo.length;
		
		Integer[] yy = new Integer[number];
		for(int i=0;i<number;i++) yy[i] = Integer.valueOf(oo[i]);
		return yy;
	}
}
