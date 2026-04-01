package a.entity.gus06.jdbc.mysql.sql.info.selectcolinfo.db;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20230224";}
	
	public static final String PATH = "information_schema.COLUMNS";
	public static final String COL_DB = "TABLE_SCHEMA";
	public static final String COL_TABLE = "TABLE_NAME";
	public static final String COL_COLUMN_NAME = "COLUMN_NAME";
	public static final String COL_COLUMN_TYPE = "COLUMN_TYPE";
	public static final String COL_COLUMN_KEY = "COLUMN_KEY";
	public static final String COL_IS_NULLABLE = "IS_NULLABLE";
	public static final String COL_EXTRA = "EXTRA";
	

	private Service format;
	
	public EntityImpl() throws Exception
	{format = Outside.service(this,"gus06.jdbc.mysql.format.sql.value");}
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		String dbName = (String) obj;
		
		return "SELECT "+COL_TABLE
		+", "+COL_COLUMN_NAME
		+", "+COL_COLUMN_TYPE
		+", "+COL_COLUMN_KEY
		+", "+COL_IS_NULLABLE
		+", "+COL_EXTRA
		
		+" FROM "+PATH+" WHERE "+COL_DB+" = "+format(dbName);
	}

	private String format(String s) throws Exception
	{return (String) format.t(s);}
}