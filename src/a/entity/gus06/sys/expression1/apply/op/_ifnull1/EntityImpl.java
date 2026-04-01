package a.entity.gus06.sys.expression1.apply.op._ifnull1;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160630";}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		return new T1(obj);
	}
	
	
	
	private class T1 implements T
	{
		private Object value;
		
		public T1(Object value)
		{this.value = value;}
		
		public Object t(Object obj) throws Exception
		{return value==null ? obj : null;}
	}
}
