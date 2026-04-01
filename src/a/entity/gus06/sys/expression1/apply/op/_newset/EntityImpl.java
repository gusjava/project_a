package a.entity.gus06.sys.expression1.apply.op._newset;

import a.framework.*;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160408";}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj instanceof Set) return new HashSet((Set) obj);
		if(obj instanceof List) return new HashSet((List) obj);
		
		return new HashSet();
	}
}
