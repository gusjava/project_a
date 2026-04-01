package a.entity.gus06.jdbc.mysql.perform.find.colinfolist.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Set;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20230225";}


	private Service buildSql;
	private Service rsToList;
	

	public EntityImpl() throws Exception
	{
		buildSql = Outside.service(this,"gus06.jdbc.mysql.sql.info.selectcolinfo.db");
		rsToList = Outside.service(this,"gus06.jdbc.resultset.toobjectmaplist");
	}
	


	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String dbName = (String) o[1];
		
		String sql = (String) buildSql.t(dbName);
		
		try
		{
			Statement st = cx.createStatement();
			ResultSet rs = st.executeQuery(sql);
			return rsToList.t(rs);
		}
		catch(Exception e)
		{
			String message = "Failed to execute sql: "+sql;
			throw new Exception(message,e);
		}
	}
}