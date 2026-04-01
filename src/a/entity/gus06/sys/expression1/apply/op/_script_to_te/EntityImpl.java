package a.entity.gus06.sys.expression1.apply.op._script_to_te;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160518";}


	private Service builder;
	private Service pToT2e;
	
	public EntityImpl() throws Exception
	{
		builder = Outside.service(this,"gus06.sys.script1.build.p");
		pToT2e = Outside.service(this,"gus06.feature.wrap.p.t2e");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		
		if(value instanceof String) return pToT2e.t(builder.t(obj));
		if(value instanceof File) return pToT2e.t(builder.t(obj));
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
}
