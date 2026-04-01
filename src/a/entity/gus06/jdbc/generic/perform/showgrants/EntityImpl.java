package a.entity.gus06.jdbc.generic.perform.showgrants;

import java.sql.Connection;
import a.framework.*;

public class EntityImpl implements Entity, T, F {

	public String creationDate() {return "20190717";}


	
	private Service handleMysql;
	private Service handlePostgresql;
	
	public EntityImpl() throws Exception
	{
		handleMysql = Outside.service(this,"gus06.jdbc.mysql.perform.showgrants");
		handlePostgresql = Outside.service(this,"gus06.jdbc.postgresql.perform.showgrants");
	}
	

	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		T t = findService((Connection) obj);
		return t.t(obj);
	}
	
	public boolean f(Object obj) throws Exception
	{
		if(obj==null) return false;
		F f = findService((Connection) obj);
		return f.f(obj);
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