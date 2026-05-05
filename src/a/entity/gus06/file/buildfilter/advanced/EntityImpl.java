package a.entity.gus06.file.buildfilter.advanced;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220208";}


	private Service name0ext;
	private Service builder;
	
	public EntityImpl() throws Exception
	{
		name0ext = Outside.service(this,"gus.x.file.getname0ext");
		builder = Outside.service(this,"gus06.sys.expression1.builder1a.f");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		return new Filter((String) obj);
	}
	
	private class Filter implements F
	{
		private F filter;
		
		public Filter(String input) throws Exception
		{filter = (F) builder.t(":"+input);}
		
		public boolean f(Object obj) throws Exception
		{
			File file = (File) obj;
			
			String name = file.getName();
			String[] n = (String[]) name0ext.t(name);
			
			String name0 = n[0];
			String ext = n[1];
			
			long size = file.length();
			String path = file.getAbsolutePath();
			String location = file.getParentFile().getAbsolutePath();
			Date lastModified = new Date(file.lastModified());
			
			Map map = new HashMap();
			
			if(file.isFile()) map.put("file",file);
			else if(file.isDirectory()) map.put("dir",file);
			
			map.put("path",path);
			map.put("location",location);
			map.put("name",name);
			map.put("name0",name0);
			map.put("ext",ext);
			map.put("size",size);
			map.put("modified",lastModified);
			
			return filter.f(map);
		}
	}
}