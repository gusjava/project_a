package a.entity.gus06.sys.filemapper1.filetoid;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160212";}
	
	public static final String TYPE_DIR = "dir";
	public static final String TYPE_ZIP = "zip";

	private Service handleDir;
	private Service handleZip;

	public EntityImpl() throws Exception
	{
		handleDir = Outside.service(this,"gus06.sys.filemapper1.filetoid.dir");
		handleZip = Outside.service(this,"gus06.sys.filemapper1.filetoid.zip");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Map root = (Map) o[0];
		String main = (String) o[1];
		File file = (File) o[2];
		
		String type = (String) get1(root,"type");
		Object src = get1(root,"src");
		
		if(type.equals("dir")) return handleDir.t(new Object[]{src,main,file});
		if(type.equals("zip")) return handleZip.t(new Object[]{src,main,file});
		throw new Exception("Unsupported type: "+type);
	}
	
	
	private Object get1(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found inside map: "+key);
		return map.get(key);
	}
}