package a.entity.gus06.sys.expression1.apply.op._tofont;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160907";}


	private Service findFont;
	
	public EntityImpl() throws Exception
	{
		findFont = Outside.service(this,"gus06.find.font");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return findFont.t(obj);
		if(obj instanceof Map) return findFont.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
