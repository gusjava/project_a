package a.entity.gus06.y.iconprovider1.loader;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250727";}

	private Service zipToLoader;
	private Service dirToLoader;

	public EntityImpl() throws Exception
	{
		zipToLoader = Outside.service(this,"gus06.y.iconprovider1.loader.zip");
		dirToLoader = Outside.service(this,"gus06.y.iconprovider1.loader.dir");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof File) return loadFromFile((File) obj);
		if(obj instanceof Object[]) return loadFromArray((Object[]) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private Object loadFromFile(File f) throws Exception
	{
		if(f.isDirectory()) return dirToLoader.t(f);
		if(f.isFile()) return zipToLoader.t(new Object[]{f,""});
		throw new Exception("Invalid path: "+f);
	}
	
	private Object loadFromArray(Object[] array) throws Exception
	{
		return zipToLoader.t(array);
	}
}