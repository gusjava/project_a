package a.entity.gus06.jdbc.mysql.perform.fk.desc.db;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170514";}


	private Service getList;
	
	public EntityImpl() throws Exception
	{
		getList = Outside.service(this,"gus06.jdbc.mysql.perform.fk.full.db");
	}
	


	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		List list = (List) getList.t(obj);
		List list1 = new ArrayList();
		
		for(int i=0;i<list.size();i++)
		{
			Map m = (Map) list.get(i);
			list1.add(mapToString(m));
		}
		return list1;
	}
	
	
	private String mapToString(Map map)
	{
		String fk_name = (String) map.get("CONSTRAINT_NAME");
		
		String table1 = (String) map.get("TABLE_NAME");
		String col1 = (String) map.get("COLUMN_NAME");
		
		String table2 = (String) map.get("REFERENCED_TABLE_NAME");
		String col2 = (String) map.get("REFERENCED_COLUMN_NAME");
		
		return fk_name+":"+table1+"@"+col1+"->"+table2+"@"+col2;
	}
}
