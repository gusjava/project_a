package a.entity.gus06.sys.expression1.apply.op._newlist;

import a.framework.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160408";}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj instanceof List) return new ArrayList((List) obj);
		if(obj instanceof Set) return new ArrayList((Set) obj);
		
		return new ArrayList();
	}
}
