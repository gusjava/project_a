package a.entity.gus06.sys.expression1.apply.op._jdbc_variable_set;

import a.framework.*;
import java.sql.Connection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170129";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.jdbc.mysql.perform.variables.setvar");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof Connection) return new T1((Connection) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private Connection cx;
		public T1(Connection cx) {this.cx = cx;}
		
		public Object t(Object obj) throws Exception
		{
			String name = (String) obj;
			return new T2(cx,name);
		}
	}
	
	
	private class T2 implements T
	{
		private Connection cx;
		private String name;
		
		public T2(Connection cx, String name)
		{
			this.cx = cx;
			this.name = name;
		}
		
		public Object t(Object obj) throws Exception
		{
			String value = ""+obj;
			return new E1(cx,name,value);
		}
	}
	
	
	private class E1 implements E
	{
		private Connection cx;
		private String name;
		private String value;
		
		public E1(Connection cx, String name, String value)
		{
			this.cx = cx;
			this.name = name;
			this.value = value;
		}
		
		public void e() throws Exception
		{
			perform.p(new Object[]{cx,name,value});
		}
	}
}
