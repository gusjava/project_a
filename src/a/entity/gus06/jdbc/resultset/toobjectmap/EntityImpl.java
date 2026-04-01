package a.entity.gus06.jdbc.resultset.toobjectmap;

import java.sql.ResultSet;
import java.util.Map;
import java.util.HashMap;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160228";}


	public Object t(Object obj) throws Exception
	{
		ResultSet rs = (ResultSet) obj;
		
		Map map = new HashMap();
		while(rs.next())
		{
			String key = rs.getString(1);
			Object value = rs.getObject(2);
			map.put(key,value);
		}
		rs.close();
		return map;
	}
}
