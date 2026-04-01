package a.entity.gus06.sys.expression1.apply.op._base_toint;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160306";}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return new T1((String)obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private String v;
		public T1(String v) {this.v = v;}
		
		public Object t(Object obj) throws Exception
		{return Integer.parseInt(v,toInt(obj));}
	}
	
	private int toInt(Object obj)
	{return Integer.parseInt(""+obj);}
}
