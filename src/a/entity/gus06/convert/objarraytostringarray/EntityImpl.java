package a.entity.gus06.convert.objarraytostringarray;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160625";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		Object[] oo = (Object[]) obj;
		int length = oo.length;
		
		String[] yy = new String[length];
		for(int i=0;i<length;i++) yy[i] = toString(oo[i]);
		return yy;
	}
	
	
	private String toString(Object o)
	{return o==null ? null : o.toString();}
}
