package a.entity.gus06.jdbc.resultset.smart;

import a.framework.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231101";}
	


	public Object t(Object obj) throws Exception
	{
		ResultSet rs = (ResultSet) obj;
		ResultSetMetaData rsmd = rs.getMetaData();
		int count = rsmd.getColumnCount();
		
		ArrayList list = new ArrayList();
		if(count==1)
		{
			while(rs.next())
			{
				Object value = rs.getObject(1);
				list.add(value);
			}
		}
		else
		{
			while(rs.next())
			{
				Map map = new HashMap();
				for(int i=0;i<count;i++)
				{
					String col = rsmd.getColumnName(i+1);
					Object value = rs.getObject(i+1);
					map.put(col,value);
				}
				list.add(map);
			}
		}
		rs.close();
		
		if(list.size()==0) return null;
		if(list.size()==1) return list.get(0);
		return list;
	}
}