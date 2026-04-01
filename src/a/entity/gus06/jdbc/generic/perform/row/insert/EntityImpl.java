package a.entity.gus06.jdbc.generic.perform.row.insert;

import a.framework.*;
import java.sql.Connection;
import java.util.Map;
import java.util.Set;
import java.sql.PreparedStatement;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.sql.Statement;
import java.sql.ResultSet;

public class EntityImpl implements Entity, P, T, F {

	public String creationDate() {return "20260109";}

	private Service handleMysql;
	private Service handlePostgresql;
	private Service handleH2;
	
	public EntityImpl() throws Exception
	{
		handleMysql = Outside.service(this,"gus06.jdbc.mysql.perform.row.insert");
		handlePostgresql = Outside.service(this,"gus06.jdbc.postgresql.perform.row.insert");
		handleH2 = Outside.service(this,"gus06.jdbc.h2.perform.row.insert");
	}

	public void p(Object obj) throws Exception
	{t(obj);}
	
	public boolean f(Object obj) throws Exception
	{return t(obj)!=null;}

	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
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
		if(url.startsWith("jdbc:h2:"))		return handleH2;
		
		throw new Exception("Unsupported url: "+url);
	}
}