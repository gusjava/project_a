package a.entity.gus06.sys.javaprojectviewer1.data.build.dataholder2;

import a.framework.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170223";}


	private Service findFiles;
	private Service buildMd5;
	private Service findClasspaths;

	public EntityImpl() throws Exception
	{
		findFiles = Outside.service(this,"gus06.dir.listing.dirtofiles.forext.jar");
		buildMd5 = Outside.service(this,"gus06.crypto.hash.md5.hexa");
		findClasspaths = Outside.service(this,"gus06.file.jar.findclasspaths");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof File) return new Holder(new File[]{(File) obj});
		if(obj instanceof File[]) return new Holder((File[]) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	
	
	private class Holder implements R
	{
		private File[] roots0;
		
		private Map md5Map;
		private Map packagesMap;
		private Map classpathsMap;
		
		private List jars;
		
		
		public Holder(File[] roots0) throws Exception
		{
			this.roots0 = roots0;
			
			md5Map = new HashMap();
			packagesMap = new HashMap();
			classpathsMap = new HashMap();
			jars = new ArrayList();
			
			for(File root0:roots0)
			{
				List files = (List) findFiles.t(root0);
				
				for(int i=0;i<files.size();i++)
				{
					File file = (File) files.get(i);
					String md5 = (String) buildMd5.t(file);
					initSet(md5Map,md5).add(file);
				}
			}
			
			int group = 0;
			Iterator it = md5Map.keySet().iterator();
			while(it.hasNext())
			{
				String md5 = (String) it.next();
				Set set = (Set) md5Map.get(md5);
				
				if(set.size()==1)
				{
					File f = (File) set.iterator().next();
					jars.add(new Object[]{f,md5,null});
				}
				else
				{
					group++;
					Iterator it1 = set.iterator();
					while(it1.hasNext())
					{
						File f = (File) it1.next();
						jars.add(new Object[]{f,md5,Integer.valueOf(group)});
					}
				}
			}
		}
		
		
		public Object r(String key) throws Exception
		{
			if(key.equals("roots0")) return roots0;
			if(key.equals("jars")) return jars;
			if(key.equals("md5Map")) return md5Map;
			if(key.equals("packagesMap")) return packagesMap;
			if(key.equals("classpathsMap")) return classpathsMap;
			
			if(key.equals("keys")) return new String[]{"roots0","jars","md5Map","packagesMap","classpathsMap"};
			
			throw new Exception("Unknown key: "+key);
		}
		
		
		private Map initMap(Map map, String key)
		{
			if(!map.containsKey(key)) map.put(key,new HashMap());
			return (Map) map.get(key);
		}
		
		private Set initSet(Map map, String key)
		{
			if(!map.containsKey(key)) map.put(key,new HashSet());
			return (Set) map.get(key);
		}
	}
}
