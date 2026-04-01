package a.entity.gus06.sys.expression1.apply.op._previous;

import a.framework.*;
import java.io.File;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151110";}
	
	
	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.iterate.previous");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof String) return perform.t((String) obj);
		if(obj instanceof Integer) return perform.t(Integer.valueOf(""+obj));
		if(obj instanceof File) return perform.t((File) obj);
		if(obj instanceof Date) return perform.t((Date) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
