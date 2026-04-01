package a.entity.gus06.jdbc.mysql.perform.find.tableset.db.col;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Set;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190516";}


	private Service buildSql;
	private Service rsToSet;
	

	public EntityImpl() throws Exception
	{
		buildSql = Outside.service(this,"gus06.jdbc.mysql.sql.info.selecttable.db.col");
		rsToSet = Outside.service(this,"gus06.jdbc.resultset.tostringset");
	}
	


	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String dbName = (String) o[1];
		String colName = (String) o[2];
		
		String sql = (String) buildSql.t(new String[]{dbName,colName});
		
		try
		{
			Statement st = cx.createStatement();
			ResultSet rs = st.executeQuery(sql);
			return rsToSet.t(rs);
		}
		catch(Exception e)
		{
			String message = "Failed to execute sql: "+sql;
			throw new Exception(message,e);
		}
	}
}
