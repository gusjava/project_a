package a.entity.gus06.data.perform.valuelist.ksort;

import a.framework.*;
import java.util.Map;
import java.io.File;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180204";}


	private Service readFile;
	private Service performMap;
	
	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus.x.file.prop.read");
		performMap = Outside.service(this,"gus06.map.build.sortedvalues.bykey");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		if(obj instanceof Map) return perform((Map) obj);
		if(obj instanceof File) return perform((File) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private List perform(Map m) throws Exception
	{return (List) performMap.t(m);}
	
	
	private List perform(File f) throws Exception
	{
		Map m = (Map) readFile.t(f);
		return perform(m);
	}
}
