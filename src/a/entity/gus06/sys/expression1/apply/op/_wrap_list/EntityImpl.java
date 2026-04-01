package a.entity.gus06.sys.expression1.apply.op._wrap_list;

import a.framework.*;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151122";}


	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		ArrayList list = new ArrayList();
		list.add(obj);
		return list;
	}
}
