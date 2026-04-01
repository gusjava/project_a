package a.entity.gus06.jdbc.resultset.toobjectlist;

import java.sql.ResultSet;
import java.util.ArrayList;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160503";}


	public Object t(Object obj) throws Exception
	{
		ResultSet rs = (ResultSet) obj;
		ArrayList list = new ArrayList();
		while(rs.next()) list.add(rs.getObject(1));
		rs.close();
		return list;
	}
}
