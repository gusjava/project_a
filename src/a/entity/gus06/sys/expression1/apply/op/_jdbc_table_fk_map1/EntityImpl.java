package a.entity.gus06.sys.expression1.apply.op._jdbc_table_fk_map1;

import a.framework.*;
import java.sql.Connection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170515";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.jdbc.mysql.perform.fk.map1.table");
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
			String dbName = (String) obj;
			return new T2(cx,dbName);
		}
	}
	
	private class T2 implements T
	{
		private Connection cx;
		private String dbName;
		
		public T2(Connection cx, String dbName)
		{
			this.cx = cx;
			this.dbName = dbName;
		}
		
		public Object t(Object obj) throws Exception
		{
			String tableName = (String) obj;
			return perform.t(new Object[]{cx,dbName,tableName});
		}
	}
}
