package a.entity.gus06.data.string.tool.replacebuilder.forlist;

import a.framework.*;
import java.util.List;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220429";}
	
	
	private Service handleFile;
	
	public EntityImpl() throws Exception
	{
		handleFile = Outside.service(this,"gus06.file.string.perform.apply.t");
	}
	

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		T t = (T) o[0];
		List list = (List) o[1];
		
		if(list.size()==2) return handle2(t, list.get(0), list.get(1));
		if(list.size()==3) return handle3(t, list.get(0), list.get(1), list.get(2));
		
		throw new Exception("Invalid list size: "+list.size());
	}
	
	private Object handle2(T t, Object o1, Object o2) throws Exception
	{
		if(o1 instanceof File) return new T2File(t, (File) o1, toString_(o2));
		return new T2String(t, toString_(o1), toString_(o2));
	}
	
	private Object handle3(T t, Object o1, Object o2, Object o3) throws Exception
	{
		if(o1 instanceof File) return new E1(t, (File) o1, toString_(o2), toString_(o3));
		return replace(t, toString_(o1), toString_(o2), toString_(o3));
	}
	
	
	
	
	private class T2File implements T
	{
		private T t;
		private File file;
		private String s1;
		
		public T2File(T t, File file, String s1)
		{
			this.t = t;
			this.file = file;
			this.s1 = s1;
		}
		
		public Object t(Object obj) throws Exception
		{return new E1(t,file,s1,toString_(obj));}
	}
	
	
	private class T2String implements T
	{
		private T t;
		private String s0;
		private String s1;
		
		public T2String(T t, String s0, String s1)
		{
			this.t = t;
			this.s0 = s0;
			this.s1 = s1;
		}
		
		public Object t(Object obj) throws Exception
		{return replace(t,s0,s1,toString_(obj));}
	}
	
	
	private class E1 implements E
	{
		private T t;
		private File file;
		private String s1;
		private String s2;
		
		public E1(T t, File file, String s1, String s2)
		{
			this.t = t;
			this.file = file;
			this.s1 = s1;
			this.s2 = s2;
		}
		
		public void e() throws Exception
		{
			T tt = new TT(t,s1,s2);
			handleFile.p(new Object[]{file,tt});
		}
	}
	
	
	private class TT implements T
	{
		private T t;
		private String s1;
		private String s2;
		
		public TT(T t, String s1, String s2)
		{
			this.t = t;
			this.s1 = s1;
			this.s2 = s2;
		}
		
		public Object t(Object obj) throws Exception
		{return t.t(new String[]{(String) obj,s1,s2});}
	}
	
	
	
	
	private String toString_(Object obj) throws Exception
	{
		if(obj instanceof Boolean) return ""+obj;
		if(obj instanceof Number) return ""+obj;
		if(obj instanceof String) return ""+obj;
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private String replace(T t, String line, String s1, String s2) throws Exception
	{return (String) t.t(new String[]{line,s1,s2});}
}