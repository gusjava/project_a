package a.entity.gus06.dir.access.check.properties;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20150524";}
	
	public static final String EXTENSION = "properties";


	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		String key = (String) o[1];
		
		File f = file(dir,key);
		return f.exists();
	}
	
	private File file(File dir, String key)
	{
		if(!dir.exists()) dir.mkdirs();
		return new File(dir,key+"."+EXTENSION);
	}
}
