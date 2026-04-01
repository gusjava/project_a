package a.entity.gus06.data.string.tool.replacebuilder.forfile;

import a.framework.*;
import java.util.List;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190331";}
	
	
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
		File file = (File) o[1];
		
		return new T1(t,file);
	}
	
	
	
	private class T1 implements T
	{
		private T t;
		private File file;
		
		public T1(T t, File file)
		{
			this.t = t;
			this.file = file;
		}
		
		public Object t(Object obj) throws Exception
		{return handleFile(t,file,obj);}
	}
	
	private Object handleFile(T t, File file, Object obj) throws Exception
	{
		String[] arr = toArray2(obj);
		if(arr!=null) return new E1(t,file,arr[0],arr[1]);
		return new T2(t,file,toString_(obj));
	}
	
	private String[] toArray2(Object obj) throws Exception
	{
		if(obj instanceof String[])
		{
			String[] arr = (String[]) obj;
			if(arr.length!=2) throw new Exception("Invalid array length: "+arr.length);
			return arr;
		}
		if(obj instanceof Object[])
		{
			Object[] arr = (Object[]) obj;
			if(arr.length!=2) throw new Exception("Invalid array length: "+arr.length);
			
			String el1 = toString_(arr[0]);
			String el2 = toString_(arr[1]);
			return new String[]{el1,el2};
		}
		if(obj instanceof List)
		{
			List list = (List) obj;
			if(list.size()!=2) throw new Exception("Invalid list size: "+list.size());
			
			String el1 = toString_(list.get(0));
			String el2 = toString_(list.get(1));
			return new String[]{el1,el2};
		}
		return null;
	}
	
	
	
	private class T2 implements T
	{
		private T t;
		private File file;
		private String s1;
		
		public T2(T t, File file, String s1)
		{
			this.t = t;
			this.file = file;
			this.s1 = s1;
		}
		
		public Object t(Object obj) throws Exception
		{return new E1(t,file,s1,toString_(obj));}
	}
	
	private String toString_(Object obj) throws Exception
	{
		if(obj instanceof Boolean) return ""+obj;
		if(obj instanceof Number) return ""+obj;
		if(obj instanceof String) return ""+obj;
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
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
}