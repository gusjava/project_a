package a.entity.gus06.convert.longarraytoobjarray;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160626";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		long[] oo = (long[]) obj;
		int number = oo.length;
		
		Long[] yy = new Long[number];
		for(int i=0;i<number;i++) yy[i] = Long.valueOf(oo[i]);
		return yy;
	}
}
