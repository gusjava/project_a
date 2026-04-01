package a.entity.gus06.sys.expression1.apply.op._div;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151110";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof Integer) return new T_div(toInt(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private int toInt(Object obj)
	{return Integer.parseInt(""+obj);}
	
	
	private class T_div implements T
	{
		private int value;
		public T_div(int value) {this.value = value;}
		
		public Object t(Object obj) throws Exception
		{return Integer.valueOf(value / toInt(obj));}
	}
}
