package a.entity.gus06.jdbc.mysql.perform.cx.usedb;

import a.framework.*;
import java.sql.Connection;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20231017";}


	private Service sqlQuery;
	
	public EntityImpl() throws Exception
	{
		sqlQuery = Outside.service(this,"gus06.jdbc.mysql.perform.sqlexecute");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String dbName = (String) o[1];
		
		String query = "use "+dbName;
		sqlQuery.p(new Object[]{cx,query});
	}
}