package a.entity.gus06.jdbc.mysql.sql.info.fk.full.db;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170322";}
	
	public static final String PATH = "information_schema.KEY_COLUMN_USAGE";
	public static final String COL_DB = "TABLE_SCHEMA";
	public static final String COL_REF_TABLE = "REFERENCED_TABLE_NAME";
	


	private Service format;
	
	public EntityImpl() throws Exception
	{format = Outside.service(this,"gus06.jdbc.mysql.format.sql.value");}
	
	
	
	public Object t(Object obj) throws Exception
	{
		String dbName = (String) obj;
		
		return "SELECT * FROM "+PATH+
			" WHERE "+COL_DB+" = "+format(dbName)+
			" AND "+COL_REF_TABLE+" IS NOT NULL";
	}

	private String format(String s) throws Exception
	{return (String) format.t(s);}
}