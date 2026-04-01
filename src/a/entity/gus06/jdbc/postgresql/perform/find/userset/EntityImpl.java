package a.entity.gus06.jdbc.postgresql.perform.find.userset;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190717";}

	public static final String SQL = "SELECT u.usename FROM pg_catalog.pg_user u";
	


	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		Connection cx = (Connection) obj;
		Statement st = cx.createStatement();
		ResultSet rs = st.executeQuery(SQL);
		
		HashSet set = new HashSet();
		while (rs.next())
		{
			String user = rs.getString(1);
			set.add(user);
		}
		return set;
	}
}
