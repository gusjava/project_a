package a.entity.gus06.jdbc.mysql.perform.fk.map1.table;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170515";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.jdbc.mysql.perform.fk.full.table");
	}
	


	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		List list = (List) perform.t(obj);
		
		Map map = new HashMap();
		for(int i=0;i<list.size();i++)
		{
			Map m = (Map) list.get(i);
			
			String columnName = get(m,"COLUMN_NAME");
			String refTable = get(m,"REFERENCED_TABLE_NAME");
			
			map.put(columnName,refTable);
		}
		return map;
	}
	
	
	private String get(Map m, String key)
	{return (String) m.get(key);}
}
