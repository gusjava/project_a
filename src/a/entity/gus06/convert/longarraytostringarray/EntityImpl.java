package a.entity.gus06.convert.longarraytostringarray;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160625";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		long[] oo = (long[]) obj;
		int number = oo.length;
		
		String[] yy = new String[number];
		for(int i=0;i<number;i++) yy[i] = ""+oo[i];
		return yy;
	}
}
