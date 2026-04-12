package a.entity.gus06.jdbc.mysql.perform.sqlexecute.tostring;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Set;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231017";}

	private Service rsTo;

	public EntityImpl() throws Exception
	{
		rsTo = Outside.service(this,"gus06.jdbc.resultset.next.tostring");
	}
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String sql = (String) o[1];
		
		try
		{
			Statement st = cx.createStatement();
			ResultSet rs = st.executeQuery(sql);
			return rsTo.t(rs);
		}
		catch(Exception e)
		{
			String message = "Failed to execute sql: "+sql;
			throw new Exception(message,e);
		}
	}
}