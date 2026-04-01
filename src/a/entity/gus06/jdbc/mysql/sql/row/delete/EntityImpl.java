package a.entity.gus06.jdbc.mysql.sql.row.delete;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.Iterator;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190324";}


	private Service formatName;
	private Service formatValue;
	
	public EntityImpl() throws Exception
	{
		formatName = Outside.service(this,"gus06.jdbc.mysql.format.sql.name");
		formatValue = Outside.service(this,"gus06.jdbc.mysql.format.sql.value");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		String path = (String) o[0];
		Object ids = o[1];
		Set pkeys = (Set) o[2];
		
		Map mapPK = buildMapPK(pkeys,ids);
		
		List keys = new ArrayList(mapPK.keySet());
		Collections.sort(keys);
		int nb = keys.size();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("DELETE FROM "+formatName.t(path)+" WHERE ");
		for(int i=0;i<nb;i++)
		{
			String key = (String) keys.get(i);
			Object value = mapPK.get(key);
			
			sql.append(" "+formatName.t(key)+"="+formatValue.t(value));
			if(i<nb-1) sql.append(" AND ");
		}
		
		return sql.toString();
	}
	
	
	private Map buildMapPK(Set pkeys, Object ids) throws Exception
	{
		if(ids instanceof Map) return (Map) ids;
		if(ids instanceof String)
		{
			if(pkeys.size()!=1) throw new Exception("Failed to build delete sql query with primary key="+ids);
			String name = (String) pkeys.iterator().next();
			Map map = new HashMap();
			map.put(name,ids);
			return map;
		}
		throw new Exception("Invalid data type: "+ids.getClass().getName());
	}
}
