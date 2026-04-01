package a.entity.gus06.sys.expression1.apply.op._path_slash;

import a.framework.*;
import java.io.File;
import java.net.URL;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220421";}
	
	public static final String SEP = "/";
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof String) return path((String) obj);
		if(obj instanceof File) return path((File) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private String path(String path)
	{return path.replace(File.separator, SEP);}
	
	private String path(File file)
	{return file.getAbsolutePath().replace(File.separator, SEP);}
}