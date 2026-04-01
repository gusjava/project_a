package a.entity.gus06.jdbc.mysql.perform.db.init1;

import a.framework.*;
import java.sql.Connection;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20161109";}


	private Service createDb;
	private Service initUser;


	public EntityImpl() throws Exception
	{
		createDb = Outside.service(this,"gus06.jdbc.mysql.perform.db.create");
		initUser = Outside.service(this,"gus06.jdbc.mysql.perform.db.inituser.std");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String dbName = (String) o[1];
		
		createDb.p(new Object[]{cx,dbName});
		initUser.p(new Object[]{cx,dbName});
	}
}