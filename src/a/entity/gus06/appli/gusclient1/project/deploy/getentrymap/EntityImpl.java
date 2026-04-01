package a.entity.gus06.appli.gusclient1.project.deploy.getentrymap;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.io.File;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20140902";}
	
	public static final String PATH_SRCDIR = "path.dev.srcdir";
	public static final String PATH_BINDIR = "path.dev.bindir";


	private Service idToDirRes;
	private Service buildValidator;
	private Service entityEntryToName;
	private Service dirToEntryMap;
	private Service fileProvider;
	private Service manager;
	
	private String resourceRootPath;
	
	
	
	public EntityImpl() throws Exception
	{
		idToDirRes = Outside.service(this,"gus06.appli.gusclient1.project.idtodir.resource");
		buildValidator = Outside.service(this,"gus06.appli.gusclient1.project.deploy.buildvalidator");
		entityEntryToName = Outside.service(this,"gus06.app.entity.entrytoname");
		dirToEntryMap = Outside.service(this,"gus06.dir.dirtoentrymap");
		fileProvider = Outside.service(this,"fileprovider");
		manager = Outside.service(this,"gus06.appli.gusclient1.project.manager");
		
		resourceRootPath = (String) Outside.resource(this,"resourcerootpath");
		resourceRootPath = p(resourceRootPath);
	}
	
	
	public Object g() throws Exception
	{
		String id = (String) manager.g();
		if(id==null) return null;
		
		File resDir = (File) idToDirRes.t(id);
		File srcDir = (File) fileProvider.r(PATH_SRCDIR);
		File binDir = (File) fileProvider.r(PATH_BINDIR);
		
		Map resEntries = (Map) dirToEntryMap.t(resDir);
		Map srcEntries = (Map) dirToEntryMap.t(srcDir);
		Map binEntries = (Map) dirToEntryMap.t(binDir);
		
		if(resEntries.isEmpty()) throw new Exception("Empty resource dir: "+resDir);
		if(srcEntries.isEmpty()) throw new Exception("Empty java src dir: "+srcDir);
		if(binEntries.isEmpty()) throw new Exception("Empty class bin dir: "+binDir);
		
		F validator = (F) buildValidator.g();
		
		Map map = new HashMap();
		
		addToMap_res(map,resEntries);
		addToMap(map,srcEntries,validator);
		addToMap(map,binEntries,validator);
		
		return map;
	}
	
	
	
	
	private void addToMap_res(Map map, Map map0) throws Exception
	{
		Iterator it = map0.keySet().iterator();
		while(it.hasNext())
		{
			String name = (String) it.next();
			File file = (File) map0.get(name);
			
			name = p(name);
			if(!name.startsWith(resourceRootPath))
			name = resourceRootPath + name;
			map.put(name,file);
		}
	}
	
	
	
	
	private void addToMap(Map map, Map map0, F validator) throws Exception
	{
		Iterator it = map0.keySet().iterator();
		while(it.hasNext())
		{
			String name = (String) it.next();
			File file = (File) map0.get(name);
			
			name = p(name);
			if(isValidEntry(name,validator))
			map.put(name,file);
		}
	}
	
	
	private boolean isValidEntry(String name, F validator) throws Exception
	{
		if(name.startsWith("gus06/framework/")) return true;
		if(name.startsWith("gus06/manager/")) return true;
		if(name.startsWith("gus06/resource/")) return false;
		if(name.startsWith("gus06/entity/")) return isValidEntityEntry(name,validator);
		
		throw new Exception("Unexpected entry name: "+name);
	}
	
	
	private boolean isValidEntityEntry(String name, F validator) throws Exception
	{
		String entityName = (String) entityEntryToName.t(name);
		return validator.f(entityName);
	}
	
	
	private String p(String name)
	{
		if(name.startsWith("/")) return name.substring(1);
		return name;
	}
}
