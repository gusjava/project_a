package a.entity.gus06.sys.expression1.apply.op._rem_regex;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170116";}
	
	private Service findNumber;
	
	public EntityImpl() throws Exception
	{findNumber = Outside.service(this,"gus06.find.number");}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return new T1((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private String value;
		public T1(String value) {this.value = value;}
		
		public Object t(Object obj) throws Exception
		{
			if(obj==null) return value;
			return rem(value,toString_(obj));
		}
		
		private String toString_(Object obj) throws Exception
		{
			if(obj instanceof Boolean) return ""+obj;
			if(obj instanceof Number) return ""+obj;
			if(obj instanceof String) return ""+obj;
			
			throw new Exception("Invalid data type: "+obj.getClass().getName());
		}
	}
		
	private Object rem(String s1, String s2) throws Exception
	{return s1.replaceAll(s2,"");}
}
