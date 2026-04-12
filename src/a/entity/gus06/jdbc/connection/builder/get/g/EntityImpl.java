package a.entity.gus06.jdbc.connection.builder.get.g;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20260110";}
	
	private Service fromString;
	private Service fromStringArray;
	private Service fromObjectArray;
	private Service fromMap;
	private Service fromFile;

	public EntityImpl() throws Exception
	{
		fromString = Outside.service(this,"gus06.jdbc.connection.builder.get.string");
		fromStringArray = Outside.service(this,"gus06.jdbc.connection.builder.get.stringarray");
		fromObjectArray = Outside.service(this,"gus06.jdbc.connection.builder.get.objectarray");
		fromMap = Outside.service(this,"gus06.jdbc.connection.builder.get.map");
		fromFile = Outside.service(this,"gus06.jdbc.connection.builder.get.file");
	}
	
	public Object t(Object obj) throws Exception
	{
		G g = (G) obj;
		Object v = g.g();
		
		if(v==null) return null;
		if(v instanceof String) return fromString.t(v);
		if(v instanceof String[]) return fromStringArray.t(v);
		if(v instanceof Object[]) return fromObjectArray.t(v);
		if(v instanceof Map) return fromMap.t(v);
		if(v instanceof File) return fromFile.t(v);
		
		throw new Exception("Invalid data type: "+v.getClass().getName());
	}
}
