package a.entity.gus06.sys.expression1.apply.op._relpath;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161207";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return new T1((String) obj);
		if(obj instanceof File) return new T1(((File) obj).getAbsolutePath());
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private String path;
		public T1(String path) {this.path = path;}
		
		public Object t(Object obj) throws Exception
		{return buildRelpath(path, toFile(obj));}
	}
	
	private File toFile(Object obj) throws Exception
	{
		if(obj instanceof File) return (File) obj;
		if(obj instanceof String) return new File((String) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private String buildRelpath(String path, File root) throws Exception
	{
		String rootPath = root.getAbsolutePath();
		
		int back = 0;
		File root0 = root;
		while(!path.startsWith(rootPath))
		{
			root0 = root0.getParentFile();
			if(root0==null) throw new Exception("Failed to build relpath for "+path+" with root="+root);
			rootPath = root0.getAbsolutePath();
			back++;
		}
		
		path = path.substring(rootPath.length());
		if(path.startsWith(File.separator)) path = path.substring(1);
		
		for(int i=0;i<back;i++)
		path = ".."+File.separator+path;
		
		return path;
	}
	
}