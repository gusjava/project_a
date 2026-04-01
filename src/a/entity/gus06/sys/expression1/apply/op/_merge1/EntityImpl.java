package a.entity.gus06.sys.expression1.apply.op._merge1;

import a.framework.*;
import java.util.Set;
import java.util.List;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160227";}


	private Service merge1;
	private Service merge2;
	
	
	public EntityImpl() throws Exception
	{
		merge1 = Outside.service(this,"gus06.map.maparray.merge.engine1");
		merge2 = Outside.service(this,"gus06.map.maplist.merge.engine1");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Map[]) return merge1.t(obj);
		if(obj instanceof List) return merge2.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}