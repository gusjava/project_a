package a.entity.gus06.sys.expression1.apply.op._resolve1;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170319";}
	
	public static final String DELIM = "{}";


	private Service perform;
	
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.map.string.resolve1");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		
		if(value==null) return null;
		if(value instanceof Map) perform.t(new Object[]{value,DELIM});
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
}
