package a.entity.gus06.jdbc.resultset.next.toobject;

import java.sql.ResultSet;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160228";}



	public Object t(Object obj) throws Exception
	{
		ResultSet rs = (ResultSet) obj;
		if(!rs.next()) {rs.close();return null;}
		
		Object res = rs.getObject(1);
		rs.close();
		return res;
	}
}
