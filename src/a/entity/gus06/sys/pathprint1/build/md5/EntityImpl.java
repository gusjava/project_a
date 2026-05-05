package a.entity.gus06.sys.pathprint1.build.md5;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20230327";}

	private Service buildMd5;

	public EntityImpl() throws Exception
	{
		buildMd5 = Outside.service(this,"gus.y.crypto1.hash.md5.hexa");
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
	
	
	
	
	private String computeFile(File file, Map map) throws Exception
	{
		if(file.isFile()) return computeFileF(file, map);
		if(file.isDirectory()) return computeFileD(file, map);
		return null;
	}
	
	private String computeFileF(File f, Map map) throws Exception
	{
		String print = "@"+buildMd5(f);
		map.put(f, print);
		return print;
	}
	
	private String computeFileD(File d, Map map) throws Exception
	{
		List listing = new ArrayList();
		File[] ff = d.listFiles();
		if(ff!=null) for(File f : ff)
		{
			String m = computeFile(f, map);
			if(m!=null) listing.add(m);
		}
		String print = listingToPrint(listing);
		map.put(d, print);
		return print;
	}
	
	private String listingToPrint(List listing) throws Exception
	{
		Collections.sort(listing);
		StringBuffer b = new StringBuffer();
		for(int i=0;i<listing.size();i++) b.append(listing.get(i)+"\n");
		return "#"+buildMd5(b.toString());
	}
	
	private String buildMd5(Object obj) throws Exception
	{return (String) buildMd5.t(obj);}
}