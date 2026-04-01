package a.entity.gus06.sys.expression1.apply.op._jdbc_updatecol_w;

import a.framework.*;
import java.sql.Connection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161020";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.jdbc.mysql.perform.rows.updatecol.where");
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
			String path = (String) obj;
			return new T2(cx,path);
		}
	}
	
	
	private class T2 implements T
	{
		private Connection cx;
		private String path;
		
		public T2(Connection cx, String path)
		{
			this.cx = cx;
			this.path = path;
		}
		
		public Object t(Object obj) throws Exception
		{
			String column = (String) obj;
			return new T3(cx,path,column);
		}
	}
	
	
	private class T3 implements T
	{
		private Connection cx;
		private String path;
		private String column;
		
		public T3(Connection cx, String path, String column)
		{
			this.cx = cx;
			this.path = path;
			this.column = column;
		}
		
		public Object t(Object obj) throws Exception
		{
			return new T4(cx,path,column,obj);
		}
	}
	
	
	private class T4 implements T
	{
		private Connection cx;
		private String path;
		private String column;
		private Object value;
		
		public T4(Connection cx, String path, String column, Object value)
		{
			this.cx = cx;
			this.path = path;
			this.column = column;
			this.value = value;
		}
		
		public Object t(Object obj) throws Exception
		{
			return new E1(cx,path,column,value,obj);
		}
	}
	
	
	private class E1 implements E
	{
		private Connection cx;
		private String path;
		private String column;
		private Object value;
		private Object where;
		
		public E1(Connection cx, String path, String column, Object value, Object where)
		{
			this.cx = cx;
			this.path = path;
			this.column = column;
			this.value = value;
			this.where = where;
		}
		
		public void e() throws Exception
		{
			perform.p(new Object[]{cx,path,column,value,where});
		}
	}
}
