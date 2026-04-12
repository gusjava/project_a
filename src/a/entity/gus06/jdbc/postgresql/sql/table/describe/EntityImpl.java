package a.entity.gus06.jdbc.postgresql.sql.table.describe;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190719";}
	
	private Service formatValue;
	
	public EntityImpl() throws Exception
	{formatValue = Outside.service(this,"gus06.jdbc.postgresql.format.sql.value");}

	public Object t(Object obj) throws Exception
	{
		String path = (String) obj;
		
		String[] nn = path.split("\\.");
		String db = nn[0];
		String table = nn[1];
		
		return "SELECT column_name, is_nullable, column_default, type_name"
			+ " FROM information_schema.COLUMNS"
			+ " WHERE table_schema="+formatValue(db)+" AND table_name="+formatValue(table);
	}
	
	private String formatValue(String s) throws Exception
	{return (String) formatValue.t(s);}
}
