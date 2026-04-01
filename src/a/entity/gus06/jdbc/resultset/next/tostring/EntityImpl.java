package a.entity.gus06.jdbc.resultset.next.tostring;

import java.sql.ResultSet;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150622";}



	public Object t(Object obj) throws Exception
	{
		ResultSet rs = (ResultSet) obj;
		if(!rs.next()) {rs.close();return null;}
		
		String res = rs.getString(1);
		rs.close();
		return res;
	}
}
