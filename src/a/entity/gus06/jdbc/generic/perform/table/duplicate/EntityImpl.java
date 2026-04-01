package a.entity.gus06.jdbc.generic.perform.table.duplicate;

import a.framework.*;
import java.sql.Connection;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20230220";}


	private Service handleMysql;
	private Service handlePostgresql;
	
	public EntityImpl() throws Exception
	{
		handleMysql = Outside.service(this,"gus06.jdbc.mysql.perform.table.duplicate");
		handlePostgresql = Outside.service(this,"gus06.jdbc.postgresql.perform.table.duplicate");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		Connection cx = (Connection) o[0];

		findService(cx).p(obj);
	}
	
	
	private Service findService(Connection cx) throws Exception
	{
		String url = cx.getMetaData().getURL();
		if(url.startsWith("jdbc:mysql:"))	return handleMysql;
		if(url.startsWith("jdbc:mariadb:"))	return handleMysql;
		if(url.startsWith("jdbc:postgresql:"))	return handlePostgresql;
		
		throw new Exception("Unsupported url: "+url);
	}
}
	
	