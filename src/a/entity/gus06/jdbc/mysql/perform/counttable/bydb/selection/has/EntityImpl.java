package a.entity.gus06.jdbc.mysql.perform.counttable.bydb.selection.has;

import a.framework.*;
import java.util.Iterator;
import java.util.Map;

public class EntityImpl implements Entity, F, T {

	public String creationDate() {return "20231217";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.jdbc.mysql.perform.counttable.bydb.selection");
	}
	
	public boolean f(Object obj) throws Exception
	{
		Map map = (Map) perform.t(obj);
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			String dbName = (String) it.next();
			Long tableCount = (Long) map.get(dbName);
			if(tableCount!=null && tableCount>0) return true;
		}
		return false;
	}
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) perform.t(obj);
		Iterator it = map.keySet().iterator();
		
		int count = 0;
		while(it.hasNext())
		{
			String dbName = (String) it.next();
			Long tableCount = (Long) map.get(dbName);
			if(tableCount!=null && tableCount>0) count++;
		}
		return count;
	}
}