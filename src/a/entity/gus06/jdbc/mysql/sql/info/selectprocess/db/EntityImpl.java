package a.entity.gus06.jdbc.mysql.sql.info.selectprocess.db;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231025";}
	
	public static final String PATH = "information_schema.processlist";
	public static final String COL_COMMAND = "command";
	public static final String COL_STATE = "state";
	public static final String COL_INFO = "info";
	public static final String COL_USER = "user";
	public static final String COL_HOST = "host";
	public static final String COL_DB = "db";
	

	private Service format;
	
	public EntityImpl() throws Exception
	{format = Outside.service(this,"gus06.jdbc.mysql.format.sql.value");}
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof String) return buildFromString((String) obj);
		if(obj instanceof List) return buildFromList((List) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private String buildFromString(String dbName) throws Exception
	{return "SELECT "+fields()+" FROM "+PATH+" WHERE "+where(dbName);}
	
	private String buildFromList(List dbNames) throws Exception
	{return "SELECT "+fields()+" FROM "+PATH+" WHERE "+where(dbNames);}
	
	private String where(String dbName) throws Exception
	{return COL_DB+" = "+format(dbName);}
	
	private String where(List dbNames) throws Exception
	{return dbNames.isEmpty() ? "FALSE" : COL_DB+" IN "+format(dbNames);}
	
	private String fields()
	{return COL_COMMAND+", "+COL_STATE+", "+COL_INFO+", "+COL_USER+", "+COL_HOST;}

	private String format(Object data) throws Exception
	{return (String) format.t(data);}
}