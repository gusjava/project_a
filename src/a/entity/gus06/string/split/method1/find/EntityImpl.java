package a.entity.gus06.string.split.method1.find;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20240303";}


	private Service fromString;
	private Service fromList;
	
	public EntityImpl() throws Exception
	{
		fromString = Outside.service(this,"gus06.string.split.method1");
		fromList = Outside.service(this,"gus06.convert.listtostringarray");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof String[]) return obj;
		if(obj instanceof String) return fromString.t(obj);
		if(obj instanceof List) return fromList.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}