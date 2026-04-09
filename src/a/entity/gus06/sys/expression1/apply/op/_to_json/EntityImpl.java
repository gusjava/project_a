package a.entity.gus06.sys.expression1.apply.op._to_json;

import a.framework.*;
import java.util.List;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20171022";}


	private Service jsonGenerator;
	
	public EntityImpl() throws Exception
	{
		jsonGenerator = Outside.service(this,"gus.x.json.build1");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Map) return jsonGenerator.t(obj);
		if(obj instanceof List) return jsonGenerator.t(obj);
		if(obj instanceof Object[]) return jsonGenerator.t(obj);
		if(obj instanceof String) return jsonGenerator.t(obj);
		if(obj instanceof Boolean) return jsonGenerator.t(obj);
		if(obj instanceof Number) return jsonGenerator.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
