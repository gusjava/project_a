package a.entity.gus06.jdbc.mysql.perform.user.create.fordb;

import java.sql.Connection;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20161101";}

	
	private Service performCreate;
	private Service performGrant;
	
	public EntityImpl() throws Exception
	{
		performCreate = Outside.service(this,"gus06.jdbc.mysql.perform.user.create");
		performGrant = Outside.service(this,"gus06.jdbc.mysql.perform.user.grant.db.all");
	}
	
	

	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=5) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String user = (String) o[1];
		String host = (String) o[2];
		String pwd = (String) o[3];
		String db = (String) o[4];
		
		performCreate.p(new Object[]{cx,user,host,pwd});
		performGrant.p(new Object[]{cx,db,user,host});
	}
}
