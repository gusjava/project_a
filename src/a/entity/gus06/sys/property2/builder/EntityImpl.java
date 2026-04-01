package a.entity.gus06.sys.property2.builder;

import a.framework.*;

public class EntityImpl implements Entity, T, G {

	public String creationDate() {return "20150911";}
	
	
	public Object g() throws Exception
	{return new Property(null);}


	public Object t(Object obj) throws Exception
	{return new Property((Class) obj);}
	
	
	
	private class Property extends S1 implements P, G, V, R, F
	{
		private Object owner;
		private String name;
		private Class type;
		
		private Object previous;
		private Object value;
		
		private F validator;
		private boolean readable;
		private boolean writable;
		
		
		public Property(Class type)
		{this.type = type;}
		
		
		public Object g() throws Exception
		{
			if(!readable)  throw new Exception("Attempt to read value on a non-readable property");
			return value;
		}
		
		
		public void p(Object obj) throws Exception
		{
			if(!writable)  throw new Exception("Attempt to write value on a non-writable property");
			if(_equals(obj,value)) return;
			
			if(!_sameType(obj,type)) throw new Exception("Invalid object type: "+obj.getClass().getName());
			if(!_isValid(obj,validator)) throw new Exception("Invalid object value: "+obj);
			
			previous = value;
			value = obj;
			modified();
		}
		
		
		public boolean f(Object obj) throws Exception
		{
			if(obj.equals("readable")) return readable;
			if(obj.equals("writable")) return writable;
			
			throw new Exception("Unknown key: "+obj);
		}
		
		
		public void v(String key, Object obj) throws Exception
		{
			if(key.equals("owner")) {owner = obj;return;}
			if(key.equals("name")) {name = (String) obj;return;}
			if(key.equals("type")) {type = (Class) obj;return;}
			if(key.equals("validator")) {validator = (F) obj;return;}
			if(key.equals("readable")) {readable = Boolean.parseBoolean((String) obj);return;}
			if(key.equals("writable")) {writable = Boolean.parseBoolean((String) obj);return;}
			
			throw new Exception("Unknown key: "+key);
		}
		
		
		public Object r(String key) throws Exception
		{
			if(key.equals("owner")) return owner;
			if(key.equals("name")) return name;
			if(key.equals("type")) return type;
			if(key.equals("previous")) return previous;
			if(key.equals("validator")) return validator;
			
			if(key.equals("keys")) return new String[]{"owner","name","type","previous","validator"};
			
			throw new Exception("Unknown key: "+key);
		}
		
		private void modified()
		{send(this,"modified()");}
		
		public String toString()
		{return value!=null?value.toString():"null";}
	}
	
	
	
	
	private boolean _isValid(Object o, F f) throws Exception
	{return f==null || f.f(o);}
	
	
	
	
	private boolean _sameType(Object o, Class c)
	{
		if(o==null || c==null) return true;
		return o.getClass().equals(c);
	}
	
	
	private boolean _equals(Object o1, Object o2)
	{
		if(o1==null && o2==null) return true;
		if(o1==null || o2==null) return false;
		return o1.equals(o2);
	}
}