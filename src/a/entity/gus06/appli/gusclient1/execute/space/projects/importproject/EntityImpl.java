package a.entity.gus06.appli.gusclient1.execute.space.projects.importproject;

import a.framework.*;
import java.util.*;
import java.io.File;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20140910";}

	public static final String ROOTPATH = "gus06/resource/gus/gyem/app/";

	private Service manager;
	private Service idToDir;
	private Service input;
	private Service extract;
	private Service findAppMap;
	private Service findArray;


	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.gusclient1.project.manager");
		idToDir = Outside.service(this,"gus06.appli.gusclient1.project.idtodir.resource");
		findAppMap = Outside.service(this,"gus06.app.jarfile.listing.resources.appmap.gyem");
		extract = Outside.service(this,"gus06.app.jarfile.extract.filemap");
		input = Outside.service(this,"gus06.input.choose.dialog");
		findArray = Outside.service(this,"gus06.find.stringarray");
	}
	
	
	public void e() throws Exception
	{
		String id = (String) manager.g();
		if(invalid(id)) return;
		
		File dir = (File) idToDir.t(id);
		Map appMap = (Map) findAppMap.g();
		
		String title = "App chooser";
		String message = "Please choose an ID:";
		
		Set keys = appMap.keySet();
		String[] keys_ = (String[]) findArray.t(keys);
		
		String id0 = (String) input.t(new Object[]{message,title,keys_});
		List paths = (List) appMap.get(id0);
		Map fileMap = buildFileMap(paths,dir,id0);
		
		extract.p(fileMap);
	}
	
	
	
	private boolean invalid(String s)
	{return s==null || s.equals("");}
	
	
	
	private Map buildFileMap(List paths, File dir, String id0)
	{
		int length = ROOTPATH.length()+id0.length()+1;
		Map m = new HashMap();
		for(Object o:paths)
		{
			String path = (String) o;
			String lastPart = path.substring(length).replace("/",File.separator);
			File file = new File(dir,lastPart);
			m.put(path,file);
		}
		return m;
	}
}
