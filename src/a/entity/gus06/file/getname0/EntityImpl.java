package a.entity.gus06.file.getname0;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141123";}

	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof File) return name0((File) obj);
		if(obj instanceof String) return name0((String) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private String name0(File file)
	{
		String name = file.getName();
		return name0(name);
	}
	
	
	private String name0(String name)
	{
		if(name.contains("\\"))
		{
			String[] n = name.split("\\\\");
			name = n[n.length-1];
		}
		if(name.contains("/"))
		{
			String[] n = name.split("/");
			name = n[n.length-1];
		}
		if(!name.contains(".")) return name;
		
		String[] n = name.split("\\.");
		String ext = n[n.length-1];
		
		return name.substring(0,name.length()-ext.length()-1);
	}
}