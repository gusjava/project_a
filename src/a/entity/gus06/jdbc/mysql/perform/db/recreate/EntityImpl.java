package a.entity.gus06.jdbc.mysql.perform.db.recreate;

import a.framework.*;
import java.sql.Connection;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20231014";}


	private Service protectedPath;
	private Service dropDb;
	private Service createDb;
	private Service getName;
	private Service useDb;

	public EntityImpl() throws Exception
	{
		protectedPath = Outside.service(this,"gus06.jdbc.mysql.check.protectedpath");
		dropDb = Outside.service(this,"gus06.jdbc.mysql.perform.db.drop");
		createDb = Outside.service(this,"gus06.jdbc.mysql.perform.db.create");
		getName = Outside.service(this,"gus06.jdbc.mysql.perform.cx.dbname");
		useDb = Outside.service(this,"gus06.jdbc.mysql.perform.cx.usedb");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String dbName = (String) o[1];
		
		if(dbName==null) throw new Exception("Db name is null");
		if(protectedPath.f(dbName)) throw new Exception("Attempt to empty db: "+dbName);
		
		String name0 = (String) getName.t(cx);
		
		dropDb.p(new Object[]{cx, dbName});
		createDb.p(new Object[]{cx, dbName});
		
		useDb.p(new Object[]{cx, name0});
	}
}