package a.entity.gus06.sys.expression1.apply.op._tomaparray;

import a.framework.*;
import java.util.List;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151201";}


	private Service fromList;
	private Service fromArray;
	
	public EntityImpl() throws Exception
	{
		fromList = Outside.service(this,"gus06.convert.listtomaparray.strict");
		fromArray = Outside.service(this,"gus06.convert.objarraytomaparray.strict");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Map[]) return obj;
		if(obj instanceof Object[]) return fromArray.t(obj);
		if(obj instanceof List) return fromList.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
