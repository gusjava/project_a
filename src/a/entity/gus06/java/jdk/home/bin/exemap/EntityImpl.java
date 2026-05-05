package a.entity.gus06.java.jdk.home.bin.exemap;

import java.io.File;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20171031";}

	
	private Service bin;
	private Service listing;
	private Service getName;
	
	private Map exeMap;
	
	
	public EntityImpl() throws Exception
	{
		bin = Outside.service(this,"gus06.java.jdk.home.bin");
		listing = Outside.service(this,"gus06.dir.listing0.ext.exe");
		getName = Outside.service(this,"gus.x.file.getname0");
	}
	
	
	public Object g() throws Exception
	{
		if(exeMap==null) init();
		return exeMap;
	}
	
	
	private void init() throws Exception
	{
		File binDir = (File) bin.g();
		File[] exe = (File[]) listing.t(binDir);
		
		exeMap = new HashMap();
		for(File f : exe)
		{
			String name = (String) getName.t(f);
			exeMap.put(name,f);
		}
	}
}
