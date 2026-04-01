package a.entity.gus06.app.jarfile.classpath.findsrc;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140829";}


	private Service entryToSrc;

	
	public EntityImpl() throws Exception
	{
		entryToSrc = Outside.service(this,"gus06.app.jarfile.entryname.findsrc");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return "";
		
		String classPath = toClassPath(obj);
		String entryName = classPath.replace(".","/")+".java";
		return entryToSrc.t(entryName);
	}
	
	
	private String toClassPath(Object obj) throws Exception
	{
		if(obj instanceof String) return (String) obj;
		if(obj instanceof Class) return ((Class) obj).getName();
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
