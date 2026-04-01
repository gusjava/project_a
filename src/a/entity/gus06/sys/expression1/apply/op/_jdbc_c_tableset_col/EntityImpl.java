package a.entity.gus06.sys.expression1.apply.op._jdbc_c_tableset_col;

import a.framework.*;
import java.sql.Connection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231023";}


	private Service find;
	private Service dbName;
	
	public EntityImpl() throws Exception
	{
		find = Outside.service(this,"gus06.jdbc.mysql.perform.find.tableset.db.col");
		dbName = Outside.service(this,"gus06.jdbc.mysql.perform.cx.dbname");
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
		public T1(Connection cx)
		{this.cx = cx;}
		
		public Object t(Object obj) throws Exception
		{
			String colName = (String) obj;
			String name = (String) dbName.t(cx);
			return find.t(new Object[]{cx,name,colName});
		}
	}
}