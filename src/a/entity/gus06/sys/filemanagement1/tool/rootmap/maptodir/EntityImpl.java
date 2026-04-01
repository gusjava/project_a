package a.entity.gus06.sys.filemanagement1.tool.rootmap.maptodir;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191128";}
	
	public static final String KEY_HDD = "HDD";
	public static final String KEY_PATH = "PATH";


	private Service hddNameToDir;


	public EntityImpl() throws Exception
	{
		hddNameToDir = Outside.service(this,"gus06.dir.hdd.drivername.inv");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		if(map==null) return null;
		
		String hddName = (String) map.get(KEY_HDD);
		String path = (String) map.get(KEY_PATH);
		
		File dir0 = (File) hddNameToDir.t(hddName);
		if(dir0==null) return null;
		
		return new File(dir0,path);
	}
}