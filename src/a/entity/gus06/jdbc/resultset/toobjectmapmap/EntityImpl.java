package a.entity.gus06.jdbc.resultset.toobjectmapmap;

import a.framework.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160229";}
	


	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		ResultSet rs = (ResultSet) o[0];
		String id = (String) o[1];
		
		return buildRs(rs,id);
	}
	
	
	
	private Map buildRs(ResultSet rs, String col0) throws Exception
	{
		ResultSetMetaData rsmd = rs.getMetaData();
		int count = rsmd.getColumnCount();
		int index0 = indexOfCol0(rsmd,count,col0);
		
		Map map1 = new HashMap();
		while(rs.next())
		{
			Map map = new HashMap();
			for(int i=0;i<count;i++)
			{
				String col = rsmd.getColumnName(i+1);
				Object value = rs.getObject(i+1);
				map.put(col,value);
			}
			Object key = rs.getObject(index0+1);
			if(map1.containsKey(key)) throw new Exception("Column "+col0+" has duplicated value: "+key);
			map1.put(key,map);
		}
		
		rs.close();
		return map1;
	}
	
	
	private int indexOfCol0(ResultSetMetaData rsmd, int count, String col0) throws Exception
	{
		for(int i=0;i<count;i++)
		if(rsmd.getColumnName(i+1).equals(col0)) return i;
		throw new Exception("Colonne not found for name: "+col0);
	}
}
