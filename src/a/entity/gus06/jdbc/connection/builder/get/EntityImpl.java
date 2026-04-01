package a.entity.gus06.jdbc.connection.builder.get;

import a.framework.*;
import java.util.Map;
import java.io.File;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150621";}

	private Service fromString;
	private Service fromStringArray;
	private Service fromObjectArray;
	private Service fromMap;
	private Service fromList;
	private Service fromFile;
	private Service fromG;

	public EntityImpl() throws Exception
	{
		fromString = Outside.service(this,"gus06.jdbc.connection.builder.get.string");
		fromStringArray = Outside.service(this,"gus06.jdbc.connection.builder.get.stringarray");
		fromObjectArray = Outside.service(this,"gus06.jdbc.connection.builder.get.objectarray");
		fromMap = Outside.service(this,"gus06.jdbc.connection.builder.get.map");
		fromList = Outside.service(this,"gus06.jdbc.connection.builder.get.list");
		fromFile = Outside.service(this,"gus06.jdbc.connection.builder.get.file");
		fromG = Outside.service(this,"gus06.jdbc.connection.builder.get.g");
	}
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		if(obj instanceof String) return fromString.t(obj);
		if(obj instanceof String[]) return fromStringArray.t(obj);
		if(obj instanceof Object[]) return fromObjectArray.t(obj);
		if(obj instanceof Map) return fromMap.t(obj);
		if(obj instanceof List) return fromList.t(obj);
		if(obj instanceof File) return fromFile.t(obj);
		if(obj instanceof G) return fromG.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
