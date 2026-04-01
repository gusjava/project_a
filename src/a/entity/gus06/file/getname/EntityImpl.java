package a.entity.gus06.file.getname;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141021";}

	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof File) return name((File) obj);
		if(obj instanceof String) return name((String) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private String name(File file)
	{
		return file.getName();
	}
	
	
	private String name(String s)
	{
		if(s.contains("\\"))
		{
			String[] n = s.split("\\\\");
			s = n[n.length-1];
		}
		if(s.contains("/"))
		{
			String[] n = s.split("/");
			s = n[n.length-1];
		}
		return s;
	}
}