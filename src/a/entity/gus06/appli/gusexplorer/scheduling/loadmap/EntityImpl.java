package a.entity.gus06.appli.gusexplorer.scheduling.loadmap;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180119";}

	public static final String KEY_SRC = "src";
	public static final String KEY_PROP_FILE = "propFile";
	public static final String KEY_SCRIPT_FILE = "scriptFile";
	
	
	private Service readFile;

	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus06.file.read.properties.strict");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File propFile = (File) obj;
		Map prop = (Map) readFile.t(propFile);
			
		if(!prop.containsKey(KEY_SRC)) throw new Exception("Src key not found inside properties");
		String src = (String) prop.get(KEY_SRC);
		
		File scriptFile = new File(src);
		if(!scriptFile.isFile()) throw new Exception("Script file not found: "+scriptFile);
		
		Map map = new HashMap(prop);
		map.put(KEY_PROP_FILE,propFile);
		map.put(KEY_SCRIPT_FILE,scriptFile);
		
		return map;
	}
}
