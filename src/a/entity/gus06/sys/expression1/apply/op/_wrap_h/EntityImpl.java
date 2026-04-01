package a.entity.gus06.sys.expression1.apply.op._wrap_h;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180202";}


	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Number) return new Wrap(((Number) obj).doubleValue());
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	public class Wrap implements H
	{
		private double data;
		public Wrap(double data) {this.data = data;}
		
		public double h(double obj) throws Exception
		{return data;}
	}
}
