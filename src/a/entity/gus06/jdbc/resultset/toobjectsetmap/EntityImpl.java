package a.entity.gus06.jdbc.resultset.toobjectsetmap;

import java.sql.ResultSet;
import java.util.Map;
import java.util.HashMap;
import a.framework.*;
import java.util.HashSet;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160503";}


	public Object t(Object obj) throws Exception
	{
		ResultSet rs = (ResultSet) obj;
		
		Map map = new HashMap();
		while(rs.next())
		{
			String key = rs.getString(1);
			Object value = rs.getObject(2);
			
			if(!map.containsKey(key))
				map.put(key,new HashSet());
			
			Set s = (Set) map.get(key);
			s.add(value);
		}
		rs.close();
		return map;
	}
}
