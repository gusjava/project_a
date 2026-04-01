package a.entity.gus06.convert.booleanarraytoobjarray;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160626";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		boolean[] oo = (boolean[]) obj;
		int number = oo.length;
		
		Boolean[] yy = new Boolean[number];
		for(int i=0;i<number;i++) yy[i] = Boolean.valueOf(oo[i]);
		return yy;
	}
}