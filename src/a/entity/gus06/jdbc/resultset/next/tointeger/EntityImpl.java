package a.entity.gus06.jdbc.resultset.next.tointeger;

import java.sql.ResultSet;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170129";}



	public Object t(Object obj) throws Exception
	{
		ResultSet rs = (ResultSet) obj;
		if(!rs.next()) {rs.close();return null;}
		
		int res = rs.getInt(1);
		rs.close();
		return Integer.valueOf(res);
	}
}
