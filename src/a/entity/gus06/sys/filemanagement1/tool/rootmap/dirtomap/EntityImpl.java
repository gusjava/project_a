package a.entity.gus06.sys.filemanagement1.tool.rootmap.dirtomap;

import a.framework.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191110";}
	
	public static final String KEY_HDD = "HDD";
	public static final String KEY_PATH = "PATH";
	public static final String KEY_STARTTIME = "STARTTIME";
	public static final String KEY_DURATION = "DURATION";
	public static final String KEY_FILENB = "FILENB";
	public static final String KEY_SPACE = "SPACE";
	public static final String KEY_ERROR = "ERROR";


	private Service getHddName;
	private Service getUprooted;


	public EntityImpl() throws Exception
	{
		getHddName = Outside.service(this,"gus06.dir.hdd.drivername");
		getUprooted = Outside.service(this,"gus06.dirfile.find.uprootedpath");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		
		String hddName = (String) getHddName.t(dir);
		String path = (String) getUprooted.t(dir);
		
		Map map = new HashMap();
		
		map.put(KEY_HDD,hddName);
		map.put(KEY_PATH,path);
		map.put(KEY_STARTTIME,"");
		map.put(KEY_DURATION,"");
		map.put(KEY_FILENB,"");
		map.put(KEY_SPACE,"");
		map.put(KEY_ERROR,"");
		
		return map;
	}
}
