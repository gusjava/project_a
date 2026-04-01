package a.entity.gus06.jdbc.resultset.toobjectmapset;

import a.framework.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20230224";}
	


	public Object t(Object obj) throws Exception
	{
		ResultSet rs = (ResultSet) obj;
		ResultSetMetaData rsmd = rs.getMetaData();
		int count = rsmd.getColumnCount();
		
		Set set = new HashSet();
		while(rs.next())
		{
			Map map = new HashMap();
			for(int i=0;i<count;i++)
			{
				String col = rsmd.getColumnName(i+1);
				Object value = rs.getObject(i+1);
				map.put(col,value);
			}
			set.add(map);
		}
		
		rs.close();
		return set;
	}
}
