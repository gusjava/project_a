package a.entity.gus06.sys.expression1.apply.op._jdbc_c1c2_setmap;

import a.framework.*;
import java.sql.Connection;
import java.sql.ResultSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160503";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.jdbc.mysql.perform.select.c1c2.as.setmap");
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
			String table = (String) obj;
			return new T2(cx,table);
		}
	}
	
	private class T2 implements T
	{
		private Connection cx;
		private String table;
		
		public T2(Connection cx, String table)
		{
			this.cx = cx;
			this.table = table;
		}
		
		public Object t(Object obj) throws Exception
		{
			String c1 = (String) obj;
			return new T3(cx,table,c1);
		}
	}
	
	private class T3 implements T
	{
		private Connection cx;
		private String table;
		private String c1;
		
		public T3(Connection cx, String table, String c1)
		{
			this.cx = cx;
			this.table = table;
			this.c1 = c1;
		}
		
		public Object t(Object obj) throws Exception
		{
			String c2 = (String) obj;
			return perform.t(new Object[]{cx,table,c1,c2});
		}
	}
}
