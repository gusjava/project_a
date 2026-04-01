package a.entity.gus06.sys.expression1.apply.op._e_throw;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160325";}


	private Service findException;
	
	public EntityImpl() throws Exception
	{
		findException = Outside.service(this,"gus06.find.exception");
	}
	
	private Exception toException(Object obj) throws Exception
	{return (Exception) findException.t(obj);}



	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		
		if(value==null) return null;
		if(value instanceof String) return new E1(toException(value));
		if(value instanceof Exception) return new E1(toException(value));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class E1 implements E
	{
		private Exception e;
		public E1(Exception e){this.e = e;}
		
		public void e() throws Exception
		{throw e;}
	}
}
