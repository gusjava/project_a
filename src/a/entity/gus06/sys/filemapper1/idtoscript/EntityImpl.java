package a.entity.gus06.sys.filemapper1.idtoscript;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231127";}
	
	public static final String KEY_TYPE = "type";
	public static final String TYPE_DIR = "dir";
	public static final String TYPE_ZIP = "zip";

	private Service handleDir;
	private Service handleZip;

	public EntityImpl() throws Exception
	{
		handleDir = Outside.service(this,"gus06.sys.filemapper1.idtoscript.dir");
		handleZip = Outside.service(this,"gus06.sys.filemapper1.idtoscript.zip");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Map root = (Map) o[0];
		String main = (String) o[1];
		String id = (String) o[2];
		
		String type = (String) get1(root,KEY_TYPE);
		
		if(type.equals(TYPE_DIR)) return handleDir.t(new Object[]{root,main,id});
		if(type.equals(TYPE_ZIP)) return handleZip.t(new Object[]{root,main,id});
		throw new Exception("Unsupported type: "+type);
	}
	
	private Object get1(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found inside map: "+key);
		return map.get(key);
	}
}