package a.entity.gus06.jdbc.mysql.sql.info.countprocess.db;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231028";}
	
	public static final String PATH = "information_schema.processlist";
	public static final String COL_DB = "db";
	public static final String COL_INFO = "info";
	

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
	{return "SELECT COUNT(*) FROM "+PATH+" WHERE "+where(dbName);}
	
	private String buildFromList(List dbNames) throws Exception
	{return "SELECT COUNT(*) FROM "+PATH+" WHERE "+where(dbNames);}
	
	private String where(String dbName) throws Exception
	{return COL_DB+" = "+format(dbName)+" AND "+COL_INFO+" IS NOT NULL";}
	
	private String where(List dbNames) throws Exception
	{return dbNames.isEmpty() ? "FALSE" : COL_DB+" IN "+format(dbNames)+" AND "+COL_INFO+" IS NOT NULL";}

	private String format(Object data) throws Exception
	{return (String) format.t(data);}
}