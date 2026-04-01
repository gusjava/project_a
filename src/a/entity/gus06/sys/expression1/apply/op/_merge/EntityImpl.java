package a.entity.gus06.sys.expression1.apply.op._merge;

import a.framework.*;
import java.util.Set;
import java.util.List;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151118";}


	private Service merge1;
	private Service merge2;
	private Service merge3;
	
	
	public EntityImpl() throws Exception
	{
		merge1 = Outside.service(this,"gus06.set.setarray.toset");
		merge2 = Outside.service(this,"gus06.map.maparray.toset");
		merge3 = Outside.service(this,"gus06.set.setlist.toset");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Set[]) return merge1.t(obj);
		if(obj instanceof Map[]) return merge2.t(obj);
		if(obj instanceof List) return merge3.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
