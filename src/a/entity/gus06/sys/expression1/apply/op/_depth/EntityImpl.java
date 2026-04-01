package a.entity.gus06.sys.expression1.apply.op._depth;

import a.framework.*;
import java.io.File;
import java.net.URL;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170113";}


	private Service performFile;
	private Service performURL;
	
	public EntityImpl() throws Exception
	{
		performFile = Outside.service(this,"gus06.dirfile.find.depth");
		performURL = Outside.service(this,"gus06.url.depth");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof URL) return performURL.t(obj);
		if(obj instanceof File) return performFile.t(obj);
		if(obj instanceof Class) return nameToDepth(getName((Class) obj));
		if(obj instanceof Entity) return nameToDepth(getName((Entity) obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private String getName(Class c)
	{
		return c.getSimpleName();
	}
	
	private String getName(Entity entity) 
	{
		String s = entity.getClass().getName();
		return s.substring(13,s.length()-11);
	}
	
	private Integer nameToDepth(String name)
	{
		int n = name.split("\\.").length;
		return Integer.valueOf(n);
	}
}
