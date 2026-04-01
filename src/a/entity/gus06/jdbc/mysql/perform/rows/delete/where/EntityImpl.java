package a.entity.gus06.jdbc.mysql.perform.rows.delete.where;

import a.framework.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20161020";}


	private Service format;
	private Service buildWhere;
	
	public EntityImpl() throws Exception
	{
		format = Outside.service(this,"gus06.jdbc.mysql.format.sql.name");
		buildWhere = Outside.service(this,"gus06.jdbc.mysql.sql.where");
	}


	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String path = (String) o[1];
		String where = (String) buildWhere.t(o[2]);
		
		String sql = "DELETE FROM "+format(path)+" WHERE "+where;
		
		try
		{
			PreparedStatement st = cx.prepareStatement(sql);
			st.executeUpdate();
		}
		catch(Exception e)
		{
			String message = "Failed to execute query: "+sql;
			throw new Exception(message,e);
		}
	}
	
	private String format(String s) throws Exception
	{return (String) format.t(s);}
}
