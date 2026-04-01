package a.entity.gus06.jdbc.mysql.sql.info.uk.full.db;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20230226";}
	
	public static final String PATH = "information_schema.KEY_COLUMN_USAGE";
	public static final String COL_DB = "TABLE_SCHEMA";
	


	private Service format;
	
	public EntityImpl() throws Exception
	{format = Outside.service(this,"gus06.jdbc.mysql.format.sql.value");}
	
	
	
	public Object t(Object obj) throws Exception
	{
		String dbName = (String) obj;
		
		return "SELECT * FROM "+PATH+
			" WHERE "+COL_DB+" = "+format(dbName)+
			" AND referenced_table_name IS NULL"+
			" AND CONSTRAINT_NAME!='PRIMARY'";
	}

	private String format(String s) throws Exception
	{return (String) format.t(s);}
}