package a.entity.gus06.jdbc.postgresql.perform.find.variablemap;

import a.framework.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190717";}

	public static final String SQL = "select name, setting from pg_settings";
	
	
	private Service rsToMap;
	
	public EntityImpl() throws Exception
	{rsToMap = Outside.service(this,"gus06.jdbc.resultset.tostringmap");}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;

		Connection cx = (Connection) obj;
		Statement st = cx.createStatement();
		ResultSet rs = st.executeQuery(SQL);
		return rsToMap.t(rs);
	}
}
