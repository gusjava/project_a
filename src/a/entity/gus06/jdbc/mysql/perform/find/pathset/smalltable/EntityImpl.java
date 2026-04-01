package a.entity.gus06.jdbc.mysql.perform.find.pathset.smalltable;

import a.framework.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150623";}

	public static final String SQL = "select table_schema, table_name, data_length from information_schema.tables where data_length < 50000";

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;

		Connection cx = (Connection) obj;
		Statement st = cx.createStatement();
		ResultSet rs = st.executeQuery(SQL);
		
		HashSet set = new HashSet();
		while(rs.next()) set.add(rs.getString(1)+"."+rs.getString(2));
		rs.close();
		return set;
	}
}
