package a.entity.gus06.sys.expression1.apply.op._child;

import a.framework.*;
import java.io.File;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160107";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof File) return new T1(obj);
		if(obj instanceof File[]) return new T1(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private Object data;
		public T1(Object data) {this.data = data;}
		
		public Object t(Object obj) throws Exception
		{return build(data,obj);}
	}
	
	
	
	private Object build(Object data, Object path) throws Exception
	{
		if(data instanceof File)
		{
			File d = (File) data;
			return buildFile(d,path);
		}
		if(data instanceof File[])
		{
			File[] d1 = (File[]) data;
			File[] d2 = new File[d1.length];
			for(int i=0;i<d1.length;i++) d2[i] = buildFile(d1[i],path);
			return d2;
		}
		throw new Exception("Invalid data type: "+data.getClass().getName());
	}
	
	
	
	private File buildFile(File dir, Object path) throws Exception
	{
		if(path instanceof String) return new File(dir,(String) path);
		if(path instanceof List) 
		{
			File f = dir;
			List ll = (List) path;
			for(Object l : ll) f = new File(f,(String) l);
			return f;
		}
		throw new Exception("Invalid path type: "+path.getClass().getName());
	}
}
