package a.entity.gus06.jdbc.generic.perform.table.create1;

import a.framework.*;
import java.sql.Connection;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20260107";}

	private Service handleMysql;
	private Service handlePostgresql;
	private Service handleH2;
	
	public EntityImpl() throws Exception
	{
		handleMysql = Outside.service(this,"gus06.jdbc.mysql.perform.table.create1");
		handlePostgresql = Outside.service(this,"gus06.jdbc.postgresql.perform.table.create1");
		handleH2 = Outside.service(this,"gus06.jdbc.h2.perform.table.create1");
	}
	
	public void p(Object obj) throws Exception
	{
		if(obj==null) return;
		
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		Connection cx = (Connection) o[0];

		P p = findService(cx);
		p.p(obj);
	}
	
	private Service findService(Connection cx) throws Exception
	{
		String url = cx.getMetaData().getURL();
		if(url.startsWith("jdbc:mysql:"))	return handleMysql;
		if(url.startsWith("jdbc:mariadb:"))	return handleMysql;
		if(url.startsWith("jdbc:postgresql:"))	return handlePostgresql;
		if(url.startsWith("jdbc:h2:"))		return handleH2;
		
		throw new Exception("Unsupported url: "+url);
	}
}