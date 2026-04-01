package a.entity.gus06.map.deep.nextdata0.g;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160312";}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2)	throw new Exception("Wrong data number: "+o.length);
		
		G data = (G) o[0];
		String key = (String) o[1];
		
		if(!key.equals("g")) throw new Exception("g is expected for G object");
		
		return data.g();
	}
}
