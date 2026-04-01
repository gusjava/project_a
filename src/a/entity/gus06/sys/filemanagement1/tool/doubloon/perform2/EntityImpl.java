package a.entity.gus06.sys.filemanagement1.tool.doubloon.perform2;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191216";}
	
	public static final String KEY_TYPE = "type";
	public static final String KEY_NAME = "name";
	public static final String KEY_SIZE = "size";
	public static final String KEY_MD5 = "md5";
	public static final String KEY_CHILDREN = "children";
	public static final String KEY_LOCATION = "location";
	public static final String KEY_ROOTNAME = "rootName";

	public static final String TYPE_FILE = "file";
	public static final String TYPE_DIR = "dir";
	public static final String TYPE_ROOT = "root";



	private Service buildTreeMap;
	private Service buildMd5;

	public EntityImpl() throws Exception
	{
		buildTreeMap = Outside.service(this,"gus06.sys.filemanagement1.explore.build.treemap");
		buildMd5 = Outside.service(this,"gus06.crypto.hash.md5.hexa");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		R engine = (R) o[0];
		Map fileMap = (Map) o[1];
		
		Map md5_places = new HashMap();
		Map md5_doubloon = new HashMap();
		
		Iterator it = fileMap.keySet().iterator();
		while(it.hasNext())
		{
			String rootName = (String) it.next();
			File file = (File) fileMap.get(rootName);
			
			if(file.isFile())
			{
				Map treeMap = (Map) buildTreeMap.t(new Object[]{file,rootName});
				handleMap(treeMap,md5_places);
			}
		}
		
		it = md5_places.keySet().iterator();
		while(it.hasNext())
		{
			String md5 = (String) it.next();
			Set set = (Set) md5_places.get(md5);
			int nb = set.size();
			
			if(nb>=2) 
			{
				Map doubloon = new HashMap();
				doubloon.put("number",nb);
				doubloon.put("places",set);
				
				md5_doubloon.put(md5,doubloon);
			}
		}
		
		Map output = new HashMap();
		
		output.put("doubloons",md5_doubloon);
		output.put("doubloonNb",md5_doubloon.size());
		
		return output;
	}
	
	
	
	
	
	private void handleMap(Map map, Map md5_places) throws Exception
	{
		if(map.get(KEY_TYPE).equals(TYPE_FILE)) return;
		
		if(!map.containsKey(KEY_MD5)) 
			throw new Exception("Md5 not found for file node: "+map.get(KEY_NAME));
		
		String md5 = (String) map.get(KEY_MD5);
		String rootName = (String) map.get(KEY_ROOTNAME);
		String location = (String) map.get(KEY_LOCATION);
		
		Set set = findSet(md5_places,md5);
		set.add(rootName+"\t"+location);
	}
	
	
	
	private Set findSet(Map m, String key)
	{
		if(!m.containsKey(key)) m.put(key,new HashSet());
		return (Set) m.get(key);
	}
}