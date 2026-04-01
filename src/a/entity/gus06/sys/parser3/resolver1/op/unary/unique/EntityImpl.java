package a.entity.gus06.sys.parser3.resolver1.op.unary.unique;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151029";}


	private Service buildEntity;

	public EntityImpl() throws Exception
	{
		buildEntity = Outside.service(this,"entityunique");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List cut = (List) o[0];
		T t = (T) o[1];
		
		Object value = t.t(cut.get(0));
		if(value==null) throw new Exception("Invalid value type for operator: null");

		if(value instanceof String)
		return buildEntity((String) value);
		
		throw new Exception("Invalid value type for operator: "+value.getClass().getName());
	}
	
	private Object buildEntity(String value) throws Exception
	{return buildEntity.t(value);}
}
