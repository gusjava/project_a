package a.entity.gus06.sys.expression1.apply.op._remover;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160325";}



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
		private String s;
		public T1(String s) {this.s = s;}
		
		public Object t(Object obj) throws Exception
		{
			if(obj==null) return null;
			if(obj instanceof String) return remove((String) obj,s);
			
			throw new Exception("Invalid data type: "+obj.getClass().getName());
		}
	}
	
	
	private String remove(String s1, String s2)
	{return s1.replace(s2,"");}
}
