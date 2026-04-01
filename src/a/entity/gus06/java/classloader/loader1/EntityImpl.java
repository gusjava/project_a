package a.entity.gus06.java.classloader.loader1;

import a.framework.*;

public class EntityImpl implements Entity, T, R {

	public String creationDate() {return "20140819";}


	private Service findEntityClass;
	
	public EntityImpl() throws Exception
	{
		findEntityClass = Outside.service(this,"classfinder");
	}
	
	
	public Object r(String key) throws Exception
	{return loadClass(key);}
	
	
	public Object t(Object obj) throws Exception
	{return r((String) obj);}
	
	
	
	
	private Class loadClass(String classpath) throws Exception
	{
		if(classpath==null) return null;
		if(classpath.startsWith("gus06.entity."))
			return (Class) findEntityClass.r(classpath);
		return Class.forName(classpath);
	}
}
