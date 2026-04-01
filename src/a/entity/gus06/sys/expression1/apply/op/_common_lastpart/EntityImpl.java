package a.entity.gus06.sys.expression1.apply.op._common_lastpart;

import a.framework.*;
import java.util.List;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170725";}


	private Service perform;
	private Service findArray;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.compare.string.common.lastpart");
		findArray = Outside.service(this,"gus06.find.stringarray");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof String[]) return perform.t(obj);
		if(obj instanceof List) return perform.t(findArray.t(obj));
		if(obj instanceof Set) return perform.t(findArray.t(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
