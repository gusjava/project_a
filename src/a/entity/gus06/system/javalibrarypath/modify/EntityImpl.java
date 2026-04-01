package a.entity.gus06.system.javalibrarypath.modify;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, P, V {

	public String creationDate() {return "20150607";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{perform = Outside.service(this,"gus06.system.javalibrarypath.modify.perform1");}
	

	public void p(Object obj) throws Exception
	{
		setLibraryPath(toPath(obj));
	}

	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("set")) {setLibraryPath(toPath(obj));return;}
		if(key.equals("add")) {addLibraryPath(toPath(obj));return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	private String toPath(Object obj) throws Exception
	{
		if(obj instanceof String) return (String) obj;
		if(obj instanceof File) return ((File) obj).getAbsolutePath();
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private void addLibraryPath(String path) throws Exception
	{
		try
		{
			String path0 = System.getProperty("java.library.path");
			String newPath = path+File.pathSeparator+path0;
		
			setJavaLibraryPathProp(newPath);
		}
		catch(Exception e)
		{
			String message = "Failed to add library path: "+path;
			throw new Exception(message, e);
		}
	}
	
	private void setLibraryPath(String path) throws Exception
	{
		try
		{
			setJavaLibraryPathProp(path);
		}
		catch(Exception e)
		{
			String message = "Failed to set library path: "+path;
			throw new Exception(message, e);
		}
	}
	
	
	private void setJavaLibraryPathProp(String value) throws Exception
	{perform.p(value);}
}