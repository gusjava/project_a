package a.entity.gus06.sys.expression1.apply.op._show1_data1;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250320";}


	private Service perform;
	
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.swing.frame.show.data1");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		return new T1(obj);
	}
	
	
	private class T1 implements T
	{
		private Object data;
		
		public T1(Object data)
		{this.data = data;}
		
		public Object t(Object obj) throws Exception
		{return new E1(data,(String) obj);}
	}
	
	
	private class E1 implements E
	{
		private Object data;
		private String title;
		
		public E1(Object data, String title)
		{
			this.data = data;
			this.title = title;	
		}
		
		public void e() throws Exception
		{perform.v(title,data);}
	}
}