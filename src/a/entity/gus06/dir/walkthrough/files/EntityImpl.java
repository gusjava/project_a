package a.entity.gus06.dir.walkthrough.files;

import a.framework.*;
import java.io.File;


public class EntityImpl implements Entity, P {

	public String creationDate() {return "20201011";}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		P handler = (P) o[1];
		
		handle(dir,handler);
	}
	
	private void handleDir(File dir, P handler)
	{
		File[] ff = dir.listFiles();
		if(ff!=null) for(File f:ff) handle(f,handler);
	}
	
	private void handle(File f, P handler)
	{
		if(f.isDirectory()) handleDir(f,handler);
		else if(f.isFile()) handle(f,handler);
	}
}
