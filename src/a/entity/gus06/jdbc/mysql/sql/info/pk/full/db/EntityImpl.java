package a.entity.gus06.jdbc.mysql.sql.info.pk.full.db;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170515";}
	
	public static final String PATH = "information_schema.key_column_usage";
	public static final String COL_DB = "table_schema";
	public static final String COLS = "TABLE_CATALOG, TABLE_NAME, TABLE_SCHEMA, COLUMN_NAME, CONSTRAINT_CATALOG, ORDINAL_POSITION";
	


	private Service format;
	
	public EntityImpl() throws Exception
	{format = Outside.service(this,"gus06.jdbc.mysql.format.sql.value");}
	
	
	
	public Object t(Object obj) throws Exception
	{
		String dbName = (String) obj;
		
		return "SELECT "+COLS+" FROM "+PATH+
			" WHERE "+COL_DB+" = "+format(dbName)+
			" AND constraint_name = 'PRIMARY'";
	}

	private String format(String s) throws Exception
	{return (String) format.t(s);}
}