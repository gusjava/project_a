package a.entity.gus06.jdbc.resultset.tostringmap;

import java.sql.ResultSet;
import java.util.Map;
import java.util.HashMap;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150625";}


	public Object t(Object obj) throws Exception
	{
		ResultSet rs = (ResultSet) obj;
		
		Map map = new HashMap();
		while(rs.next())
		{
			String key = rs.getString(1);
			String value = rs.getString(2);
			map.put(key,value);
		}
		rs.close();
		return map;
	}
}
