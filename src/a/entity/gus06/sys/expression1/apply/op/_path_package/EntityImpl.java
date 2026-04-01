package a.entity.gus06.sys.expression1.apply.op._path_package;

import a.framework.*;
import java.io.File;
import java.net.URL;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220825";}
	
	public static final String SEP = "\\";
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof String) return pathToPackage((String) obj);
		if(obj instanceof File) return pathToPackage((File) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private String pathToPackage(File file) throws Exception
	{return pathToPackage(file.getAbsolutePath());}
	
	
	private String pathToPackage(String path) throws Exception
	{
		String[] n = path.split("\\.",2);
		String path0 = n[0];
		String ext = n.length==2 ? n[1] : null;
		
		if(ext!=null && ext.contains(File.separator))
		throw new Exception("Invalid path: "+path);
		
		path0 = path0.replace(File.separator, ".");
		while(path0.startsWith(".")) path0 = path0.substring(1);
		
		return path0;
	}
}