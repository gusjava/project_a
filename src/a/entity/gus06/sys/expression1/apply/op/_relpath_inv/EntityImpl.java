package a.entity.gus06.sys.expression1.apply.op._relpath_inv;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220825";}
	
	
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
		private String relpath;
		public T1(String relpath) {this.relpath = relpath;}
		
		public Object t(Object obj) throws Exception
		{return buildPath(relpath, toFile(obj));}
	}
	
	private File toFile(Object obj) throws Exception
	{
		if(obj instanceof File) return (File) obj;
		if(obj instanceof String) return new File((String) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private String buildPath(String relpath, File root) throws Exception
	{
		if(relpath.startsWith(File.separator))
			return buildPath(relpath.substring(1), root);
			
		if(relpath.startsWith("."+File.separator))
			return buildPath(relpath.substring(2), root);
			
		if(relpath.startsWith(".."+File.separator))
			return buildPath(relpath.substring(3), root.getParentFile());
		
		return new File(root, relpath).getAbsolutePath();
	}
	
}