package a.entity.gus06.file.zip.run.zip.buildmap;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.nio.charset.Charset;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.util.zip.ZipOutputStream;
import java.util.zip.ZipEntry;
import java.io.FileInputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250323";}

	private Service listing;
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing.topaths");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object input = obj;
		
		if(input instanceof Map) return fromMap((Map) input);
		if(input instanceof File) return fromFile((File) input);
		if(input instanceof File[]) return fromFileArray((File[]) input);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private Map fromMap(Map input) throws Exception
	{
		Map map = new HashMap();
		Iterator it = input.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			File file = (File) input.get(key);
			
			if(file.isFile()) map.put(key,file);
			else if(file.isDirectory())
			{
				int rootLength = file.getAbsolutePath().length();
				List l = buildListing(file);
				for(int i=0;i<l.size();i++)
				{
					File f = (File) l.get(i);
					String relPath = entryName(f,rootLength);
					
					StringBuffer b = new StringBuffer();
					b.append(key);
					if(!relPath.startsWith("/")) b.append("/");
					b.append(relPath);
					
					map.put(b.toString(), f);
				}
			}
		}
		
		return map;
	}
	
	private Map fromFile(File input) throws Exception
	{
		Map map = new HashMap();
		if(input.isFile()) map.put(input.getName(),input);
		else if(input.isDirectory())
		{
			int rootLength = input.getAbsolutePath().length();
			List l = buildListing(input);
			for(int i=0;i<l.size();i++)
			{
				File f = (File) l.get(i);
				String entryName = entryName(f,rootLength);
				map.put(entryName, f);
			}
		}
		return map;
	}
	
	private Map fromFileArray(File[] input) throws Exception
	{
		Map map = new HashMap();
		for(File f : input) map.putAll(fromFile(f));
		return map;
	}
	
	
	
	private List buildListing(File input) throws Exception
	{return (List) listing.t(input);}
	
	private String entryName(File f, int rootLength)
	{
		String n = f.getAbsolutePath().substring(rootLength).replace(File.separator,"/");
		while(n.startsWith("/")) n = n.substring(1);
		if(f.isDirectory() && !n.endsWith("/")) n = n+"/";
		return n;
	}
}