package a.entity.gus06.sys.javaprojectviewer1.data.build.dataholder1;

import a.framework.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170221";}
	
	public static final String KEY_CLASSTYPE = "classtype";
	public static final String KEY_CLASSNAME = "classname";
	public static final String KEY_CLASSPATH = "classpath";
	public static final String KEY_PACKAGE = "package";
	public static final String KEY_IMPORTS = "imports";
	public static final String KEY_ROOTDIR = "rootdir";
	public static final String KEY_JAVAFILE = "javafile";



	private Service findFiles;
	private Service extract;
	private Service checkImportType;

	public EntityImpl() throws Exception
	{
		findFiles = Outside.service(this,"gus06.dir.listing.dirtofiles.forext.java");
		extract = Outside.service(this,"gus06.java.srcfile.extract.classdata.checked");
		checkImportType = Outside.service(this,"gus06.sys.javaprojectviewer1.data.check.importtype");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof File) return new Holder(new File[]{(File) obj});
		if(obj instanceof File[]) return new Holder((File[]) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Map extractData(File file)
	{
		try{return (Map) extract.t(file);}
		catch(Exception e)
		{
			String message = "File to extract data from file: "+file;
			Outside.err(this,"extractData(File)",new Exception(message,e));
		}
		return null;
	}
	
	
	
	private class Holder implements R
	{
		private File[] roots0;
		
		private Map rootsMap;
		private Map packagesMap;
		private Map classpathsMap;
		private Map importsMap;
		
		
		public Holder(File[] roots0) throws Exception
		{
			this.roots0 = roots0;
			
			rootsMap = new HashMap();
			packagesMap = new HashMap();
			classpathsMap = new HashMap();
			importsMap = new HashMap();
			
			for(File root0:roots0)
			{
				List files = (List) findFiles.t(root0);
				int len0 = root0.getParentFile().getAbsolutePath().length()+1;
				
				for(int i=0;i<files.size();i++)
				{
					File file = (File) files.get(i);
					if(isValid(file)) handleFile(file,len0);
				}
			}
			importsMap = rebuildImportsMap(importsMap,packagesMap.keySet(),classpathsMap.keySet());
		}
		
		
		
		private void handleFile(File file, int len0) throws Exception
		{
			Map data = (Map) extract.t(file);
			if(data==null) return;
			
			File root = (File) data.get(KEY_ROOTDIR);
			String classPath = (String) data.get(KEY_CLASSPATH);
			String className = (String) data.get(KEY_CLASSNAME);
			String package1 = (String) data.get(KEY_PACKAGE);
			Set imports = (Set) data.get(KEY_IMPORTS);
			
			String rootRelPath = root.getAbsolutePath().substring(len0);
			Map rootMap = initMap(rootsMap,rootRelPath);
			Map rootPackageMap = initMap(rootMap,package1);
			rootPackageMap.put(className,data);
			
			Map packageMap = initMap(packagesMap,package1);
			packageMap.put(className,data);
			
			if(classpathsMap.containsKey(classPath))
				throw new Exception("Class path found many times: "+classPath);
			classpathsMap.put(classPath,data);
			
			Iterator it = imports.iterator();
			while(it.hasNext())
			{
				String import1 = (String) it.next();
				Map importMap = initMap(importsMap,import1);
				importMap.put(classPath,data);
			}
		}
		
		
		public Object r(String key) throws Exception
		{
			if(key.equals("roots0")) return roots0;
			if(key.equals("rootsMap")) return rootsMap;
			if(key.equals("packagesMap")) return packagesMap;
			if(key.equals("classpathsMap")) return classpathsMap;
			if(key.equals("importsMap")) return importsMap;
			
			if(key.equals("keys")) return new String[]{"roots0","rootsMap","packagesMap","classpathsMap","importsMap"};
			
			throw new Exception("Unknown key: "+key);
		}
		
		
		private Map initMap(Map map, String key)
		{
			if(!map.containsKey(key)) map.put(key,new HashMap());
			return (Map) map.get(key);
		}
	
	
		private boolean isValid(File file)
		{
			String name = file.getName();
			return !name.equals("package-info.java");
		}
	}
	
	
	
	
	
	private Map rebuildImportsMap(Map map, Set packages, Set classpaths) throws Exception
	{
		Map map1 = new HashMap();
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			Object value = map.get(key);
			
			String type = checkImportType(key,packages,classpaths);
			map1.put(key+type,value);
		}
		return map1;
	}
	
	
	private String checkImportType(String key, Set packages, Set classpaths) throws Exception
	{return (String) checkImportType.t(new Object[]{key,packages,classpaths});}
}
