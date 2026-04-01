package a.entity.gus06.sys.expression1.apply.op._jdbc_c_exportdb_todir1;

import a.framework.*;
import java.sql.Connection;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231023";}


	private Service perform;
	private Service dbName;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.jdbc.mysql.perform.db.export.todir1");
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
			File dir = (File) obj;
			return new E1(cx,dir);
		}
	}
	
	private class E1 implements E
	{
		private Connection cx;
		private File dir;
		
		public E1(Connection cx, File dir)
		{
			this.cx = cx;
			this.dir = dir;
		}
		
		public void e() throws Exception
		{
			String name = (String) dbName.t(cx);
			perform.p(new Object[]{cx,name,dir});
		}
	}
}