package a.entity.gus06.string.indexof.first;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160904";}

	
	
	public Object t(Object obj) throws Exception
	{
		String[] o = (String[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String str = o[0];
		String findStr = o[1];
		
		int index = str.indexOf(findStr);
		return index==-1 ? null : Integer.valueOf(index);
	}
}
