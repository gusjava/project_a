package a.entity.gus06.sys.expression1.apply.op._tooperator0;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160211";}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		return new Operator(obj);
	}
	
	
	private class Operator implements T
	{
		private Object value;
		public Operator(Object value)
		{this.value = value;}
		
		public Object t(Object obj) throws Exception
		{
			Object[] o = (Object[]) obj;
			if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
			
			return value;
		}
	}
}
