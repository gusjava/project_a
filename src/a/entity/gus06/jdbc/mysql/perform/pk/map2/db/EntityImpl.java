package a.entity.gus06.jdbc.mysql.perform.pk.map2.db;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20230303";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.jdbc.mysql.perform.pk.full.db");
	}
	


	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		List list = (List) perform.t(obj);
		
		Map map = new HashMap();
		for(int i=0;i<list.size();i++)
		{
			Map m = (Map) list.get(i);
			
			String tableName = get(m,"TABLE_NAME");
			String columnName = get(m,"COLUMN_NAME");
			
			if(!map.containsKey(tableName))
				map.put(tableName, new ArrayList());
			((List) map.get(tableName)).add(columnName);
		}
		return map;
	}
	
	
	private String get(Map m, String key)
	{return (String) m.get(key);}
}
