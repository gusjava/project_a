package a.entity.gus06.sys.expression1.apply.op._is_insidedir;

import a.framework.*;
import java.io.File;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200415";}
	


	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		return new Filter(obj);
	}
	
	
	private class Filter implements F
	{
		private String path;
		public Filter(Object data) throws Exception
		{path = toPath(data);}
		
		public boolean f(Object obj) throws Exception
		{
			if(path==null) return false;
			if(obj==null) return false;
			
			if(obj instanceof File[])
			{
				File[] ff = (File[]) obj;
				for(File f : ff) if(path.startsWith(toPath(f))) return true;
				return false;
			}
			
			if(obj instanceof String[])
			{
				String[] ss = (String[]) obj;
				for(String s : ss) if(path.startsWith(s)) return true;
				return false;
			}
			
			if(obj instanceof List)
			{
				List list = (List) obj;
				for(Object o : list) if(path.startsWith(toPath(o))) return true;
				return false;
			}
			
			return path.startsWith(toPath(obj));
		}
	}
	
	
	
	private String toPath(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof String) return (String) obj;
		if(obj instanceof File) return ((File) obj).getAbsolutePath();
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
