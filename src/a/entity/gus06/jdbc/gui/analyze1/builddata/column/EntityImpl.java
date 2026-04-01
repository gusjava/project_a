package a.entity.gus06.jdbc.gui.analyze1.builddata.column;

import a.framework.*;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20230225";}
	
	public static final String COL1_DB = "TABLE_SCHEMA";
	public static final String COL1_TABLE = "TABLE_NAME";
	public static final String COL1_COLUMN_NAME = "COLUMN_NAME";
	public static final String COL1_COLUMN_TYPE = "COLUMN_TYPE";
	public static final String COL1_COLUMN_KEY = "COLUMN_KEY";
	public static final String COL1_IS_NULLABLE = "IS_NULLABLE";
	public static final String COL1_EXTRA = "EXTRA";


	public EntityImpl() throws Exception
	{
	}
	
	
	public Object t(Object obj) throws Exception
	{return new Holder((String) obj);}
	
	
	
	private class Holder implements R, P
	{
		private String colName;
		private Set tables = new HashSet();
		
		public Holder(String colName)
		{this.colName = colName;}
		
		public void p(Object obj) throws Exception
		{
			Map map = (Map) obj;
			
			String tableName = (String) map.get(COL1_TABLE);
			String colType = (String) map.get(COL1_COLUMN_TYPE);
			String colKey = (String) map.get(COL1_COLUMN_KEY);
			String nullable = (String) map.get(COL1_IS_NULLABLE);
			String extra = (String) map.get(COL1_EXTRA);
			
			if(tables.contains(tableName)) throw new Exception("Column found many times: "+tableName+"@"+colName);
			tables.add(tableName);
		}
		
		public Object r(String key) throws Exception
		{
			return null;
		}
	}
	
	
	private void increase(Map m, String key)
	{
		if(!m.containsKey(key)) m.put(key,1);
		else m.put(key,((Integer) m.get(key))+1);
	}
}