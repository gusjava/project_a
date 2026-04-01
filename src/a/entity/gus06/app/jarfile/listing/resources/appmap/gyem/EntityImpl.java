package a.entity.gus06.app.jarfile.listing.resources.appmap.gyem;

import java.io.File;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import a.framework.*;


public class EntityImpl implements Entity, G {

	public String creationDate() {return "20140910";}

	public static final String START = "gus06/resource/gus/gyem/app/";
	public static final int LENGTH = START.length();
	
	
	private Service resourcesListing;
	private Map map;


	public EntityImpl() throws Exception
	{
		resourcesListing = Outside.service(this,"gus06.app.jarfile.listing.resources");
	}
	
	
	public Object g() throws Exception
	{
		if(map==null) init();
		return map;
	}
	
	
	private void init() throws Exception
	{
		map = new HashMap();
		
		List listing = (List) resourcesListing.g();
		for(Object o:listing)
		{
			String path = (String) o;
			if(path.startsWith(START))
			{
				String part = path.substring(LENGTH);
				String appId = part.split("/")[0];
				add(appId,path);
			}
		}
	}
	
	
	
	private void add(String key, String value)
	{
		if(!map.containsKey(key)) map.put(key,new ArrayList());
		List list = (List) map.get(key);
		list.add(value);
	}
}
