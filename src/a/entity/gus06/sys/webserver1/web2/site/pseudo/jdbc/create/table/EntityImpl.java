package a.entity.gus06.sys.webserver1.web2.site.pseudo.jdbc.create.table;

import a.framework.*;
import java.sql.Connection;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141009";}


	private Service executeSql;


	public EntityImpl() throws Exception
	{
		executeSql = Outside.service(this,"gus06.jdbc.mysql.perform.sqlexecute");
	}
	
	public void p(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		String sql = buildSql();
		executeSql.p(new Object[]{cx,sql});
	}
	
	private String buildSql()
	{
		return "CREATE TABLE IF NOT EXISTS pseudo "+
			"(ID CHAR(10), "+
			"g TEXT, p TEXT, q TEXT, y TEXT, sign TEXT, ip TEXT, "+
			"time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "+
			"PRIMARY KEY (ID))";
	}
}
