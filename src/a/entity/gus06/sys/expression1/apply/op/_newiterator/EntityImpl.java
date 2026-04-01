package a.entity.gus06.sys.expression1.apply.op._newiterator;

import a.framework.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180322";}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj instanceof List) return ((List) obj).iterator();
		if(obj instanceof Set) return ((Set) obj).iterator();
		
		return new ArrayList();
	}
}
