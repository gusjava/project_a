package a.entity.gus06.file.jar.entry.toclasspath;

import a.framework.*;
import java.util.jar.JarEntry;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150703";}

	public static final String JARPATHSEP = "/";
	
	public Object t(Object obj) throws Exception
	{
		String name = toEntryName(obj);
		String name0 = getName0(name);
		
		if(name0.startsWith(JARPATHSEP)) name0 = name0.substring(1);
		return name0.replace(JARPATHSEP,".");
	}
	
	private String toEntryName(Object obj) throws Exception
	{
		if(obj instanceof String) return (String) obj;
		if(obj instanceof JarEntry) return ((JarEntry) obj).getName();
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private String getName0(String name)
	{
		if(!name.contains(".")) return name;
		String[] n = name.split("\\.");
		String ext = n[n.length-1];
		return name.substring(0,name.length()-ext.length()-1);
	}
}
