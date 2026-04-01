package a.entity.gus06.file.getname0ext;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160602";}

	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof File) return name0Ext((File) obj);
		if(obj instanceof String) return name0Ext(extractName((String) obj));
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private String[] name0Ext(File file) throws Exception
	{
		String name = file.getName();
		return name0Ext(name);
	}
	
	private String[] name0Ext(String name) throws Exception
	{
		try
		{
			String[] n = name.split("\\.",-1);
			if(n.length==1) return new String[] {name, ""};
			
			String ext = n[n.length-1].toLowerCase();
			String name0 = name.substring(0,name.length()-ext.length()-1);
			return new String[]{name0,ext};
		}
		catch(StringIndexOutOfBoundsException e)
		{
			throw new Exception("name0Ext failed for name: ["+name+"]", e);
		}
	}
	
	private String extractName(String path)
	{
		if(!path.contains("/") && !path.contains("\\")) return path;
		String[] n = path.split("[/\\\\]+");
		return n[n.length-1];
	}
}