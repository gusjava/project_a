package a.entity.gus06.sys.dirdoubloon1.compute.md5;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221218";}


	private Service readImage;
	private Service buildMd5;
	private Service sortByPath;
	private Service findUniqueParent;
	private Service findUniqueName;

	public EntityImpl() throws Exception
	{
		readImage = Outside.service(this,"gus06.file.read.image.preview");
		buildMd5 = Outside.service(this,"gus06.crypto.hash.md5.hexa");
		sortByPath = Outside.service(this,"gus06.sys.dirdoubloon1.tool.files.sort.bypath");
		findUniqueParent = Outside.service(this,"gus06.sys.dirdoubloon1.tool.files.find.uniqueparent");
		findUniqueName = Outside.service(this,"gus06.sys.dirdoubloon1.tool.files.find.uniquename");
	}
	
	public Object t(Object obj) throws Exception
	{
		Map sizeMap = (Map) obj;
		Map results = new HashMap();
		
		Iterator it = sizeMap.keySet().iterator();
		while(it.hasNext())
		{
			Long size = (Long) it.next();
			Set files = (Set) sizeMap.get(size);
			if(files.size()>1) handleSize(size, files, results);
		}
		return results;
	}
	
	
	
	private void handleSize(Long size, Set files1, Map results) throws Exception
	{
		Map md5Map = mapByMd5(files1);
		Iterator it = md5Map.keySet().iterator();
		while(it.hasNext())
		{
			String md5 = (String) it.next();
			Set files2 = (Set) md5Map.get(md5);
			if(files2.size()>1) handleSizeMd5(size, md5, files2, results);
		}
	}
	
	
	
	private void handleSizeMd5(Long size, String md5, Set files2, Map results) throws Exception
	{
		List files = new ArrayList(files2);
		sortByPath.p(files);
		
		int nb = files.size();
		long lost = size * (nb-1);
		File file = (File) files.get(0);
		Object image = readImage.t(file);
		
		File uniqueParent = (File) findUniqueParent.t(files);
		String uniqueName = (String) findUniqueName.t(files);
		
		Map result = new HashMap();
		results.put(md5, result);
		
		result.put("nb",nb);
		result.put("lost",lost);
		result.put("files",files);
		result.put("file",file);
		result.put("md5",md5);
		result.put("size",size);
		result.put("image",image);
		
		if(uniqueParent!=null) result.put("uniqueParent",uniqueParent);
		if(uniqueName!=null) result.put("uniqueName",uniqueName);
		
	}
	
	
	
	private Set findSet(Map map, Object key)
	{
		if(!map.containsKey(key))
			map.put(key,new HashSet());
		return (Set) map.get(key);
	}
	
	
	
	private Map mapByMd5(Set files) throws Exception
	{
		Map map = new HashMap();
		Iterator it = files.iterator();
		while(it.hasNext())
		{
			File f = (File) it.next();
			String md5 = (String) buildMd5.t(f);
			findSet(map, md5).add(f);
		}
		return map;
	}
}
