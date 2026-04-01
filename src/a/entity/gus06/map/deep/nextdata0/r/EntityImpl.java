package a.entity.gus06.map.deep.nextdata0.r;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160312";}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2)	throw new Exception("Wrong data number: "+o.length);
		
		R data = (R) o[0];
		String key = (String) o[1];
		
		return data.r(key);
	}
}
