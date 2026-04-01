package a.entity.gus06.sys.expression1.apply.op._e_throw_onnull;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160414";}
	
	
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
		obj = o[0];
		
		return new T1(obj);
	}
	
	
	
	private class T1 implements T
	{
		private Object value;
		
		public T1(Object value)
		{this.value = value;}
		
		public Object t(Object obj) throws Exception
		{return new E1(value,obj);}
	}
	
	
	private class E1 implements E
	{
		private Object value;
		private Object data;
		
		public E1(Object value, Object data)
		{
			this.value = value;
			this.data = data;
		}
		
		public void e() throws Exception
		{
			if(value==null)
			{
				Exception e = toException(data);
				throw e;
			}
		}
	}
}
