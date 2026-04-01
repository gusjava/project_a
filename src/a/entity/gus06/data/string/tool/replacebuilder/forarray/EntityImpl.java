package a.entity.gus06.data.string.tool.replacebuilder.forarray;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190331";}
	

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		T t = (T) o[0];
		String[] arr = (String[]) o[1];
		
		if(arr.length==3) return replace(t,arr[0],arr[1],arr[2]);
		if(arr.length==2) return new T2(t,arr[0],arr[1]);
		
		throw new Exception("Invalid array length: "+arr.length);
	}
	
	
	private class T2 implements T
	{
		private T t;
		private String s0;
		private String s1;
		
		public T2(T t, String s0, String s1)
		{
			this.t = t;
			this.s0 = s0;
			this.s1 = s1;
		}
		
		public Object t(Object obj) throws Exception
		{return replace(t,s0,s1,toString_(obj));}
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