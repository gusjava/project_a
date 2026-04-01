package a.entity.gus06.system.prop.userdir.modify;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P, G, T {

	public String creationDate() {return "20151111";}
	
	public static final String KEY = "user.dir";
	
	
	public Object g() throws Exception
	{return userDir();}
	
	public void p(Object obj) throws Exception
	{initUserDir(obj);}
	
	public Object t(Object obj) throws Exception
	{
		File previous = userDir();
		initUserDir(obj);
		return previous;
	}
	
	
	private File userDir()
	{return new File(System.getProperty(KEY));}
	
	
	private void initUserDir(Object obj) throws Exception
	{System.setProperty(KEY,toPath(obj));}
	
	
	private String toPath(Object obj) throws Exception
	{
		if(obj instanceof String) return (String) obj;
		if(obj instanceof File) return ((File) obj).getAbsolutePath();
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
