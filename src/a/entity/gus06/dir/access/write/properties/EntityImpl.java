package a.entity.gus06.dir.access.write.properties;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20150524";}
	
	public static final String EXTENSION = "properties";


	private Service write;
	private Service remove;

	public EntityImpl() throws Exception
	{
		write = Outside.service(this,"gus06.file.write.properties");
		remove = Outside.service(this,"gus06.file.perform.remove");
	}
	
	
	public void p(Object obj) throws Exception
	{t(obj);}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		String key = (String) o[1];
		Object data = o[2];
		
		File f = file(dir,key);
		
		if(data==null) remove.p(f);
		else write.p(new Object[]{f,data});
		
		return f;
	}
	
	private File file(File dir, String key)
	{
		if(!dir.exists()) dir.mkdirs();
		return new File(dir,key+"."+EXTENSION);
	}
}
