package a.entity.gus06.sys.javaprojectviewer1.root.build.packagemap;

import a.framework.*;
import java.io.File;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170220";}


	private Service findFiles;
	private Service extract;


	public EntityImpl() throws Exception
	{
		findFiles = Outside.service(this,"gus06.dir.listing.dirtofiles.forext.java");
		extract = Outside.service(this,"gus06.java.srcfile.extract.package1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File root = (File) obj;
		
		List files = (List) findFiles.t(root);
		int len = root.getAbsolutePath().length();
		
		Map map = new HashMap();
		for(int i=0;i<files.size();i++)
		{
			File file = (File) files.get(i);
			handleFile(map,file,len);
		}
		return map;
	}
	
	
	private void handleFile(Map map, File file, int len)
	{
		try
		{
			String package1 = getPackage(file,len);
			String package2 = (String) extract.t(file);
			
			if(!package1.equals(package2))
				throw new Exception("Invalid java file location: (package1="+package1+" & package2="+package2);
			
			initSet(map,package1).add(file);
		}
		catch(Exception e)
		{
			Outside.err(this,"handleFile(Map,File)",e);
			initSet(map,"*").add(file);
		}
	}
	
	
	
	private Set initSet(Map map, String key)
	{
		if(!map.containsKey(key)) map.put(key,new HashSet());
		return (Set) map.get(key);
	}
	
	private String getPackage(File file, int len)
	{
		File dir = file.getParentFile();
		String relPath = dir.getAbsolutePath().substring(len);
		String p = relPath.replace(File.separator,".");
		
		if(p.startsWith(".")) p = p.substring(1);
		if(p.endsWith(".")) p = p.substring(0,p.length()-1);
		
		return p;
	}
}
