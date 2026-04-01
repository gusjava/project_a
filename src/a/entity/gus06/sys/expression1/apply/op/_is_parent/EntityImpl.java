package a.entity.gus06.sys.expression1.apply.op._is_parent;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220421";}
	


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return Boolean.FALSE;
		if(!(obj instanceof File)) return new Filter(((File) obj).getAbsolutePath());
		if(!(obj instanceof String)) return new Filter((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class Filter implements F
	{
		private String dirPath;
		public Filter(String dirPath) {this.dirPath = dirPath;}
		
		public boolean f(Object obj) throws Exception
		{
			File f = (File) obj;
			return f.getParentFile().getAbsolutePath().equals(dirPath);
		}
	}
}