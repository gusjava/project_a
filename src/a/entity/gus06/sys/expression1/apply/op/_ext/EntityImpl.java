package a.entity.gus06.sys.expression1.apply.op._ext;

import a.framework.*;
import java.io.File;
import java.net.URL;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151110";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof URL) return ext((URL) obj);
		if(obj instanceof File) return ext((File) obj);
		if(obj instanceof String) return ext((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private String ext(URL url)
	{return ext(url.getFile());}
	
	private String ext(File file)
	{return ext(file.getName());}
	
	private String ext(String name)
	{
		if(!name.contains(".")) return "";
		String[] n = name.split("\\.");
		return n[n.length-1].toLowerCase();
	}
}
