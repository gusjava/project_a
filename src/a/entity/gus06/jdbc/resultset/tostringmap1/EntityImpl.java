package a.entity.gus06.jdbc.resultset.tostringmap1;

import java.sql.ResultSet;
import java.util.Map;
import java.util.HashMap;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160503";}


	public Object t(Object obj) throws Exception
	{
		ResultSet rs = (ResultSet) obj;
		
		Map map = new HashMap();
		while(rs.next())
		{
			String key = rs.getString(1);
			String value = rs.getString(2);
			
			if(map.containsKey(key)) throw new Exception("Duplicated key found: "+key);
			map.put(key,value);
		}
		rs.close();
		return map;
	}
}
