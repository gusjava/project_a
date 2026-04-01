package a.entity.gus06.sys.expression1.apply.op._wrap_set;

import a.framework.*;
import java.util.HashSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151122";}


	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		HashSet set = new HashSet();
		set.add(obj);
		return set;
	}
}
