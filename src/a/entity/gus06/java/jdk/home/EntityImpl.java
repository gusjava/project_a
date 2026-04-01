package a.entity.gus06.java.jdk.home;

import java.io.File;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20171031";}
	
	public static final String KEY = "JAVA_HOME";

	
	private Service fileMapProvider;
	
	private File dir;

	public EntityImpl() throws Exception
	{
		fileMapProvider = Outside.service(this,"gus06.system.env.filemap");
	}
	
	
	public Object g() throws Exception
	{
		if(dir==null) init();
		return dir;
	}
	
	
	private void init() throws Exception
	{
		Map fileMap = (Map) fileMapProvider.g();
		if(!fileMap.containsKey(KEY)) return;
    		
		dir = (File) fileMap.get(KEY);
	}
}
