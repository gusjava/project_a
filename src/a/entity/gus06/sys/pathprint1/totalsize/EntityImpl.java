package a.entity.gus06.sys.pathprint1.totalsize;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20230327";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = new HashMap();
		handleObj(obj,map);
		return map;
	}
	
	
	private void handleObj(Object obj, Map map) throws Exception
	{
		if(obj instanceof File) computeFile((File) obj, map);
		else if(obj instanceof File[]) handleFileArray((File[]) obj, map);
		else if(obj instanceof List) handleList((List) obj, map);
		else throw new Exception("Unsupported data type: "+obj.getClass().getName());
	}
	
	private void handleFileArray(File[] files, Map map) throws Exception
	{
		for(File file : files) computeFile(file, map);
	}
	
	private void handleList(List list, Map map) throws Exception
	{
		for(int i=0;i<list.size();i++) computeFile((File) list.get(i), map);
	}
	
	
	
	
	private long computeFile(File file, Map map) throws Exception
	{
		if(file.isFile()) return computeFileF(file, map);
		if(file.isDirectory()) return computeFileD(file, map);
		return 0;
	}
	
	private long computeFileF(File f, Map map) throws Exception
	{
		long size = f.length();
		map.put(f, size);
		return size;
	}
	
	private long computeFileD(File d, Map map) throws Exception
	{
		long size = 0;
		File[] ff = d.listFiles();
		if(ff!=null) for(File f : ff) size += computeFile(f, map);
		map.put(d, size);
		return size;
	}
}