package a.entity.gus06.jdbc.resultset.next.tolong;

import java.sql.ResultSet;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231031";}



	public Object t(Object obj) throws Exception
	{
		ResultSet rs = (ResultSet) obj;
		if(!rs.next()) {rs.close();return null;}
		
		long res = rs.getLong(1);
		rs.close();
		return Long.valueOf(res);
	}
}