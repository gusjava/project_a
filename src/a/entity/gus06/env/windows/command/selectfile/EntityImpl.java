package a.entity.gus06.env.windows.command.selectfile;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P, F {

	public String creationDate() {return "20201123";}



	public void p(Object obj) throws Exception
	{f(obj);}
	
	public boolean f(Object obj) throws Exception
	{
		File file = toFile(obj);
		if(!file.exists()) return false;
		
		String path = file.getAbsolutePath();
		Runtime.getRuntime().exec("explorer.exe /select,"+path);
		return true;
	}

	private File toFile(Object obj) throws Exception
	{
		if(obj instanceof File) return (File) obj;
		if(obj instanceof String) return new File((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
