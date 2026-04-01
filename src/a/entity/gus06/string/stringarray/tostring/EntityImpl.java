package a.entity.gus06.string.stringarray.tostring;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151203";}

	
	
	public Object t(Object obj) throws Exception
	{
		String[] ss = (String[]) obj;
		
		StringBuffer b = new StringBuffer();
		for(String s:ss) b.append(s);
		return b.toString();
	}
}