package a.entity.gus06.sys.expression1.apply.op._excludeall_empty;

import a.framework.*;
import java.util.List;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231107";}


	private Service perform;
	private Service filter;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.findall");
		filter = Outside.service(this,"gus06.data.filter.isnempty");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof List) return perform(obj);
		if(obj instanceof Set) return perform(obj);
		if(obj instanceof Object[]) return perform(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	public Object perform(Object obj) throws Exception
	{return perform.t(new Object[]{obj,filter});}
}