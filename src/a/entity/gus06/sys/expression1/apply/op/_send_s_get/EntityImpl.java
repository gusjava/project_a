package a.entity.gus06.sys.expression1.apply.op._send_s_get;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180323";}
	
	
	private Service send;

	public EntityImpl() throws Exception
	{
		send = Outside.service(this,"gus06.sys.apachehttp.m.get");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof Map) return send.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
