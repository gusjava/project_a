package a.entity.gus06.jdbc.mysql.perform.table.has;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import a.framework.*;


public class EntityImpl implements Entity, F {

	public String creationDate() {return "20150804";}



	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String tableName = (String) o[1];
		
		if(!tableName.contains("."))
			return hasTable(cx,tableName);
		return checkPath(cx,tableName);
	}
	
	
	
	private boolean hasTable(Connection cx, String tableName) throws Exception
	{
		DatabaseMetaData dbmd = cx.getMetaData();
		ResultSet rs = dbmd.getTables(null,null,tableName,null);
		return rs.next();
	}
	
	
	
	private boolean checkPath(Connection cx, String tableName) throws Exception
	{
		try
		{
			String sql = "select count(*) from "+tableName;
			Statement st = cx.createStatement();
			st.executeQuery(sql);
			st.close();
			return true;
		}
		catch(SQLException e)
		{
			if(e.toString().endsWith("doesn't exist")) return false;
			throw e;
		}
	}
}
