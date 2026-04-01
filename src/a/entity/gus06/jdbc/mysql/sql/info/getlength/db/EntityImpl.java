package a.entity.gus06.jdbc.mysql.sql.info.getlength.db;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150623";}
	
	public static final String PATH = "information_schema.tables";
	public static final String COL_DB = "table_schema";
	public static final String SUM_LENGTH = "sum(data_length+index_length)";
	


	private Service format;
	
	public EntityImpl() throws Exception
	{format = Outside.service(this,"gus06.jdbc.mysql.format.sql.value");}
	
	
	
	public Object t(Object obj) throws Exception
	{
		String dbName = (String) obj;
		return "SELECT "+SUM_LENGTH+" FROM "+PATH+" WHERE "+COL_DB+" = "+format(dbName);
	}

	private String format(String s) throws Exception
	{return (String) format.t(s);}
}
