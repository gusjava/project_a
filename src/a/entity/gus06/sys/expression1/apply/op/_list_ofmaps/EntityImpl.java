package a.entity.gus06.sys.expression1.apply.op._list_ofmaps;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160816";}


	private Service builder1;
	private Service builder2;
	
	
	public EntityImpl() throws Exception
	{
		builder1 = Outside.service(this,"gus06.list.map.builder1");
		builder2 = Outside.service(this,"gus06.list.map.builder2");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		
		if(value==null) return null;
		
		if(value instanceof List) return builder1.t(value);
		if(value instanceof Object[]) return builder1.t(value);
		if(value instanceof Object[][]) return builder2.t(value);
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
}
