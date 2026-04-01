package a.entity.gus06.jdbc.mysql.perform.find.userset;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150624";}

	public static final String SQL = "select User, Host from mysql.user";
	


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
			String host = rs.getString(2);
			set.add(user+"@"+host);
		}
		return set;
	}
}
