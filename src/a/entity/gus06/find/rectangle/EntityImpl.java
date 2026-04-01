package a.entity.gus06.find.rectangle;

import a.framework.*;
import java.awt.Rectangle;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170306";}


	private Service fromIntArray;
	private Service fromList;
	private Service fromString;

	public EntityImpl() throws Exception
	{
		fromIntArray = Outside.service(this,"gus06.convert.intarraytorectangle");
		fromList = Outside.service(this,"gus06.convert.listtorectangle");
		fromString = Outside.service(this,"gus06.convert.stringtorectangle");
	}

	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof Rectangle) return obj;
		if(obj instanceof List) return fromList.t(obj);
		if(obj instanceof int[]) return fromIntArray.t(obj);
		if(obj instanceof String) return fromString.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
