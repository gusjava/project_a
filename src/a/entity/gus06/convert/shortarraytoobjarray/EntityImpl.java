package a.entity.gus06.convert.shortarraytoobjarray;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160626";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		short[] oo = (short[]) obj;
		int number = oo.length;
		
		Short[] yy = new Short[number];
		for(int i=0;i<number;i++) yy[i] = Short.valueOf(oo[i]);
		return yy;
	}
}
