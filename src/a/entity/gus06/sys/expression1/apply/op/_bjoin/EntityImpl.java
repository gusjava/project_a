package a.entity.gus06.sys.expression1.apply.op._bjoin;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180110";}


	private Service perform;


	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.bjoin");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Object[][])		return new T1(obj);
		if(obj instanceof int[][])		return new T1(obj);
		if(obj instanceof long[][])		return new T1(obj);
		if(obj instanceof boolean[][])		return new T1(obj);
		if(obj instanceof double[][])		return new T1(obj);
		if(obj instanceof float[][])		return new T1(obj);
		if(obj instanceof char[][])		return new T1(obj);
		if(obj instanceof short[][])		return new T1(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class T1 implements T
	{
		private Object data;
		public T1(Object data) {this.data = data;}
		
		public Object t(Object obj) throws Exception
		{
			String s = (String) obj;
			return new T2(data,s);
		}
	}
	
	private class T2 implements T
	{
		private Object data;
		private String glue1;
		
		public T2(Object data, String glue1)
		{
			this.data = data;
			this.glue1 = glue1;
		}
		
		public Object t(Object obj) throws Exception
		{
			String s = (String) obj;
			return perform.t(new Object[]{data,glue1,s});
		}
	}
}
