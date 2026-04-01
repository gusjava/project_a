package a.entity.gus06.jdbc.resultset.toobjectset;

import java.sql.ResultSet;
import java.util.HashSet;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160503";}


	public Object t(Object obj) throws Exception
	{
		ResultSet rs = (ResultSet) obj;
		HashSet set = new HashSet();
		while(rs.next()) set.add(rs.getObject(1));
		rs.close();
		return set;
	}
}
