package a.entity.gus06.y.entitydb1.cx.initdb.entity_import;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20251215";}

	public static final String TABLE_NAME = "entity_import";

	public static final String COL_ENTITY_NAME = "entity_name";
	public static final String COL_ENTITY_IMPORT = "entity_import";
	public static final String COL_ENTITY_IMPORT_PACKAGE = "entity_import_package";
	public static final String COL_ENTITY_IMPORT_WILDCARD = "entity_import_wildcard";

	public static final String DEF_ENTITY_NAME = "VARCHAR(200) NOT NULL";
	public static final String DEF_ENTITY_IMPORT = "VARCHAR(500) NOT NULL";
	public static final String DEF_ENTITY_IMPORT_PACKAGE = "VARCHAR(500) NOT NULL";
	public static final String DEF_ENTITY_IMPORT_WILDCARD = "BOOLEAN NOT NULL";
	
	
	public void p(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;

		{
			String sql = "CREATE TABLE " + TABLE_NAME + " ("
			 + COL_ENTITY_NAME + " " + DEF_ENTITY_NAME
			 + ", " + COL_ENTITY_IMPORT + " " + DEF_ENTITY_IMPORT
			 + ", " + COL_ENTITY_IMPORT_PACKAGE + " " + DEF_ENTITY_IMPORT_PACKAGE
			 + ", " + COL_ENTITY_IMPORT_WILDCARD + " " + DEF_ENTITY_IMPORT_WILDCARD
			 + ", PRIMARY KEY (" + COL_ENTITY_NAME + "," + COL_ENTITY_IMPORT + "))";
			
			execute(cx, sql);
		}
		
		{
			String sql = "CREATE INDEX idx_entity_import_wildcard ON " 
			 + TABLE_NAME + "("
			 + COL_ENTITY_IMPORT + ","
			 + COL_ENTITY_IMPORT_WILDCARD+")";
			
			execute(cx, sql);
		}
		
		{
			String sql = "CREATE INDEX idx_entity_import_package_wildcard ON " 
			 + TABLE_NAME + "("
			 + COL_ENTITY_IMPORT_PACKAGE + ","
			 + COL_ENTITY_IMPORT_WILDCARD+")";
			
			execute(cx, sql);
		}
	}

	private void execute(Connection cx, String sql) throws SQLException
	{
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}