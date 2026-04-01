package a.entity.gus06.find.stringmap;

import a.framework.*;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20260113";}

	private Service fromFile;
	private Service fromString;
	
	public EntityImpl() throws Exception
	{
		fromFile = Outside.service(this,"gus06.convert.filetomap");
		fromString = Outside.service(this,"gus06.convert.stringtomap");
	}
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		if(obj instanceof Map) return obj;
		if(obj instanceof File) return fromFile.t(obj);
		if(obj instanceof String[]) return fromString.t(obj);
		if(obj instanceof String) return fromString.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
