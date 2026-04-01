package a.entity.gus06.map.deep.nextdata1.set;

import a.framework.*;
import java.util.Set;
import java.util.Iterator;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160312";}


	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2)	throw new Exception("Wrong data number: "+o.length);
		
		Set data = (Set) o[0];
		String key = (String) o[1];
		
		if(!key.equals("next")) throw new Exception("next is expected for Set object");
		
		if(data.isEmpty()) data.add(new HashMap());
		return data.iterator().next();
	}
}
