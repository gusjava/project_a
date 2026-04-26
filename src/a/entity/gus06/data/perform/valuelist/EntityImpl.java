package a.entity.gus06.data.perform.valuelist;

import a.framework.*;
import java.util.Map;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180204";}


	private Service readFile;
	
	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus.x.file.prop.read");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		if(obj instanceof Map) return perform((Map) obj);
		if(obj instanceof File) return perform((File) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private List perform(Map m)
	{
		return new ArrayList(m.values());
	}
	
	private List perform(File f) throws Exception
	{
		Map m = (Map) readFile.t(f);
		return perform(m);
	}
}
