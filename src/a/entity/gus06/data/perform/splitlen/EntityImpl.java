package a.entity.gus06.data.perform.splitlen;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151130";}


	private Service fromString;
	private Service fromList;

	public EntityImpl() throws Exception
	{
		fromString = Outside.service(this,"gus06.data.perform.splitlen.string");
		fromList = Outside.service(this,"gus06.data.perform.splitlen.list");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		
		if(input instanceof String) return fromString.t(obj);
		if(input instanceof List) return fromList.t(obj);
		
		throw new Exception("Invalid data type: "+input.getClass().getName());
	}
}
