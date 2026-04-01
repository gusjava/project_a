package a.entity.gus06.jdbc.mysql.sql.info.counttable.bydb;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20231109";}
	
	public static final String SQL = "select TABLE_SCHEMA, COUNT(TABLE_SCHEMA) as count FROM information_schema.TABLES GROUP BY TABLE_SCHEMA";
	
	public Object g() throws Exception
	{return SQL;}
}