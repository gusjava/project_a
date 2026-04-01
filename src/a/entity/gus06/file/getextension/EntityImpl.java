package a.entity.gus06.file.getextension;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140929";}

	
	public Object t(Object obj) throws Exception
	{
		String name = toFileName(obj);
		if(!name.contains(".")) return "";
		String[] n = name.split("\\.");
		return n[n.length-1];
	}
	
	private String toFileName(Object obj) throws Exception
	{
		if(obj instanceof String) return (String) obj;
		if(obj instanceof File) return ((File) obj).getName();
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
