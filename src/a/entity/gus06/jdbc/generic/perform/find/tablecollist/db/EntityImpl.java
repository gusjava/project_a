package a.entity.gus06.jdbc.generic.perform.find.tablecollist.db;

import a.framework.*;
import java.sql.Connection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190821";}

	
	private Service handleMysql;
	private Service handlePostgresql;
	
	public EntityImpl() throws Exception
	{
		handleMysql = Outside.service(this,"gus06.jdbc.mysql.perform.find.tablecollist.db");
		handlePostgresql = Outside.service(this,"gus06.jdbc.postgresql.perform.find.tablecollist.db");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		Connection cx = (Connection) o[0];

		T t = findService(cx);
		return t.t(obj);
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