package a.entity.gus06.sys.expression1.apply.op._action;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170314";}
	
	
	
	private Service buildAction;
	
	public EntityImpl() throws Exception
	{
		buildAction = Outside.service(this,"gus06.swing.action.builder0.frommap");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof Map) return buildAction.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
