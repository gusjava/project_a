package a.entity.gus06.sys.property1.builder;

import a.framework.*;

public class EntityImpl implements Entity, T, G {

	public String creationDate() {return "20150910";}
	
	
	public Object g() throws Exception
	{return new Property(null);}


	public Object t(Object obj) throws Exception
	{return new Property((Class) obj);}
	
	
	
	private class Property extends S1 implements P, G
	{
		private Class type;
		private Object value;
		
		public Property(Class type)
		{this.type = type;}
		
		public Object g() throws Exception
		{return value;}
		
		public void p(Object obj) throws Exception
		{
			if(_equals(obj,value)) return;
			if(!_sameType(obj,type)) throw new Exception("Invalid object type: "+obj.getClass().getName());
			
			value = obj;
			modified();
		}
		
		private void modified()
		{send(this,"modified()");}
		
		public String toString()
		{return value!=null?value.toString():"null";}
	}
	
	
	
	
	
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