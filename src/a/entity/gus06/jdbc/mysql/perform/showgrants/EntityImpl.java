package a.entity.gus06.jdbc.mysql.perform.showgrants;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import a.framework.*;

public class EntityImpl implements Entity, T, F {

	public String creationDate() {return "20150613";}

	public static final String SQL = "SHOW GRANTS";
	


	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		Connection cx = (Connection) obj;
		DatabaseMetaData dbmd = cx.getMetaData();
		String user = dbmd.getUserName();
		
		String line = getNext(cx);
		return isAllPriv(line) ? user+"*" : user; 
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		if(obj==null) return false;
		
		Connection cx = (Connection) obj;
		String line = getNext(cx);
		return isAllPriv(line);
	}
	
	
	
	private String getNext(Connection cx) throws Exception
	{
		Statement st = null;
		try
		{
			st = cx.createStatement();
			ResultSet rs = st.executeQuery(SQL);
			
			if(!rs.next())
			throw new Exception("SHOW GRANTS sql query did not return");
			return rs.getString(1);
		}
		finally
		{if(st!=null) st.close();}
	}
	
	
	private boolean isAllPriv(String line)
	{return line.toLowerCase().startsWith("grant all privileges on *.* to '");}
}
