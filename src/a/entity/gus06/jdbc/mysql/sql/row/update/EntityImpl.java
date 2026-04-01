package a.entity.gus06.jdbc.mysql.sql.row.update;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.Iterator;

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
		Map map = (Map) o[1];
		Set pkeys = (Set) o[2];
		
		List keys = new ArrayList(map.keySet());
		List keys1 = new ArrayList();
		
		Iterator it = pkeys.iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			if(!map.containsKey(key)) throw new Exception("Primary key not found inside map: "+key);
			keys.remove(key);
			keys1.add(key);
		}
		
		Collections.sort(keys);
		Collections.sort(keys1);
		
		int nb = keys.size();
		int nb1 = keys1.size();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("UPDATE "+formatName.t(path)+" SET");
		for(int i=0;i<nb;i++)
		{
			String key = (String) keys.get(i);
			Object value = map.get(key);
			
			sql.append(" "+formatName.t(key)+"="+formatValue.t(value));
			if(i<nb-1) sql.append(",");
		}
		
		sql.append(" WHERE ");
		for(int i=0;i<nb1;i++)
		{
			String key1 = (String) keys1.get(i);
			Object value = map.get(key1);
			
			sql.append(" "+formatName.t(key1)+"="+formatValue.t(value));
			if(i<nb1-1) sql.append(" AND ");
		}
		
		return sql.toString();
	}
}
