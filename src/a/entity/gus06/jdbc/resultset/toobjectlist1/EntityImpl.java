package a.entity.gus06.jdbc.resultset.toobjectlist1;

import java.sql.ResultSet;
import java.util.ArrayList;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160503";}


	public Object t(Object obj) throws Exception
	{
		ResultSet rs = (ResultSet) obj;
		ArrayList list = new ArrayList();
		while(rs.next())
		{
			Object value = rs.getObject(1);
			if(list.contains(value)) throw new Exception("Duplicated value found: "+value);
			list.add(value);
		}
		rs.close();
		return list;
	}
}
