package a.entity.gus06.sys.javaprojectviewer1.root0.build.classpathmap;

import a.framework.*;
import java.io.File;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170220";}


	private Service findFiles;
	private Service extract;


	public EntityImpl() throws Exception
	{
		findFiles = Outside.service(this,"gus06.dir.listing.dirtofiles.forext.java");
		extract = Outside.service(this,"gus06.java.srcfile.extract.classpath.checked");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File root = (File) obj;
		
		List files = (List) findFiles.t(root);
		
		Map map = new HashMap();
		for(int i=0;i<files.size();i++)
		{
			File file = (File) files.get(i);
			if(isValid(file)) handleFile(map,file);
		}
		return map;
	}
	
	
	private void handleFile(Map map, File file)
	{
		try
		{
			String classpath = (String) extract.t(file);
			if(map.containsKey(classpath))
				throw new Exception("classpath found many times: "+classpath);
			map.put(classpath,file);
		}
		catch(Exception e)
		{
			Outside.err(this,"handleFile(Map,File)",e);
		}
	}
	
	
	private boolean isValid(File file)
	{
		String name = file.getName();
		return !name.equals("package-info.java");
	}
}
