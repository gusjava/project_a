package a.entity.gus06.jdbc.mysql.sql.info.selecttable.db.col;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190516";}
	
	public static final String PATH = "information_schema.columns";
	public static final String COL_DB = "table_schema";
	public static final String COL_TABLE = "table_name";
	public static final String COL_COLUMN = "column_name";
	

	private Service format;
	
	public EntityImpl() throws Exception
	{format = Outside.service(this,"gus06.jdbc.mysql.format.sql.value");}
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		String[] o = (String[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String dbName = o[0];
		String colName = o[1];
		
		return "SELECT "+COL_TABLE+" FROM "+PATH
			+" WHERE "+COL_DB+" = "+format(dbName)
			+" AND "+COL_COLUMN+" = "+format(colName);
	}

	private String format(String s) throws Exception
	{return (String) format.t(s);}
}
