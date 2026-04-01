package a.entity.gus06.sys.expression1.apply.op._base;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160306";}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof Integer) return new T1(toInt(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private int v;
		public T1(int v) {this.v = v;}
		
		public Object t(Object obj) throws Exception
		{return Integer.toString(v,toInt(obj));}
	}
	
	private int toInt(Object obj)
	{return Integer.parseInt(""+obj);}
}
