package a.entity.gus06.jdbc.h2.sql.table.describe;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20260108";}
	
	private Service formatValue;
	
	public EntityImpl() throws Exception
	{formatValue = Outside.service(this,"gus06.jdbc.h2.format.sql.value");}

	public Object t(Object obj) throws Exception
	{
		String path = (String) obj;
		
		String[] nn = path.split("\\.");
		String db = nn[0];
		String table = nn[1];
		
		return "SELECT column_name, is_nullable, column_default, data_type, "
		+ "character_maximum_length, numeric_precision, numeric_scale "
		+ "FROM information_schema.columns "
		+ "WHERE table_schema = " + formatValue(db)
		+ " AND table_name = " + formatValue(table);
	}
	
	private String formatValue(String s) throws Exception
	{return (String) formatValue.t(s);}
}
