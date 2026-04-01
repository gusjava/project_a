package a.entity.gus06.jdbc.mysql.perform.db.inituser.std;

import a.framework.*;
import java.sql.Connection;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20161109";}


	private Service createUser;
	private Service grantUser;

	public EntityImpl() throws Exception
	{
		createUser = Outside.service(this,"gus06.jdbc.mysql.perform.user.create");
		grantUser = Outside.service(this,"gus06.jdbc.mysql.perform.user.grant.db.all");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String dbName = (String) o[1];
		
		String user = dbName;
		String pwd = dbName;
		String host = "localhost";
		
		createUser.p(new Object[]{cx,user,host,pwd});
		grantUser.p(new Object[]{cx,dbName,user,host});
	}
}
