package a.entity.gus06.sys.expression1.apply.op._atan;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151113";}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Number) return Double.valueOf(Math.atan(_d(obj)));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private double _d(Object obj)
	{return Double.parseDouble(""+obj);}
}
