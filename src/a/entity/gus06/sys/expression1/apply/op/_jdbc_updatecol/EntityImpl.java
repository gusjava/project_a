package a.entity.gus06.sys.expression1.apply.op._jdbc_updatecol;

import a.framework.*;
import java.sql.Connection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161020";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.jdbc.mysql.perform.rows.updatecol");
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
			String tableName = (String) obj;
			return new T2(cx,tableName);
		}
	}
	
	
	private class T2 implements T
	{
		private Connection cx;
		private String tableName;
		
		public T2(Connection cx, String tableName)
		{
			this.cx = cx;
			this.tableName = tableName;
		}
		
		public Object t(Object obj) throws Exception
		{
			String column = (String) obj;
			return new T3(cx,tableName,column);
		}
	}
	
	
	private class T3 implements T
	{
		private Connection cx;
		private String tableName;
		private String column;
		
		public T3(Connection cx, String tableName, String column)
		{
			this.cx = cx;
			this.tableName = tableName;
			this.column = column;
		}
		
		public Object t(Object obj) throws Exception
		{
			return new E1(cx,tableName,column,obj);
		}
	}
	
	
	private class E1 implements E
	{
		private Connection cx;
		private String tableName;
		private String column;
		private Object value;
		
		public E1(Connection cx, String tableName, String column, Object value)
		{
			this.cx = cx;
			this.tableName = tableName;
			this.column = column;
			this.value = value;
		}
		
		public void e() throws Exception
		{
			perform.p(new Object[]{cx,tableName,column,value});
		}
	}
}
