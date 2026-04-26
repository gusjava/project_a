package a.entity.gus06.sys.store.process.p.map;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140907";}


	private Service findMap;
	private Service readProp;


	public EntityImpl() throws Exception
	{
		findMap = Outside.service(this,"gus06.sys.store.map.find");
		readProp = Outside.service(this,"gus.x.file.prop.read");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Map) return (Map) obj;
		if(obj instanceof File) return readProp((File) obj);
		if(obj instanceof String) return findMap((String) obj);
    	
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Map findMap(String rule) throws Exception
	{return (Map) findMap.r(rule);}
	
	
	private Map readProp(File file) throws Exception
	{return (Map) readProp.t(file);}
}
