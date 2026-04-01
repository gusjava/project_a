package a.entity.gus06.convert.bytearraytoobjarray;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160626";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		byte[] oo = (byte[]) obj;
		int number = oo.length;
		
		Byte[] yy = new Byte[number];
		for(int i=0;i<number;i++) yy[i] = Byte.valueOf(oo[i]);
		return yy;
	}
}
