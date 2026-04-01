package a.entity.gus06.sys.filemanagement1.tool.dirmap.findlocation;

import a.framework.*;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250614";}
	
	public static final String KEY_LOCATION = "location";
	public static final String KEY_ROOTNAME = "rootName";

	private Service rootMapToDir;
	
	public EntityImpl() throws Exception
	{
		rootMapToDir = Outside.service(this,"gus06.sys.filemanagement1.tool.rootmap.maptodir");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		Map dirMap = (Map) o[1];
		
		String rootName = (String) dirMap.get(KEY_ROOTNAME);
		String location = (String) dirMap.get(KEY_LOCATION);
		
		if(rootName==null) throw new Exception("rootName key not found inside dirMap");
		if(location==null) throw new Exception("location key not found inside dirMap");
		
		Map mapRoot = (Map) ((R) engine).r("mapRoot:"+rootName);
		if(mapRoot==null) throw new Exception("Root undefined for name: "+rootName);
		
		File rootDir = (File) rootMapToDir.t(mapRoot);
		if(rootDir==null) throw new Exception("Root dir not found: "+rootDir);
		if(!rootDir.isDirectory()) throw new Exception("Root dir not found: "+rootDir);
		
		File locationDir = new File(rootDir, location);
		if(locationDir==null) throw new Exception("Location dir not found: "+locationDir);
		if(!locationDir.isDirectory()) throw new Exception("Location dir not found: "+locationDir);
			
		return locationDir;
	}
}