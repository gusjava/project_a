package a.entity.gus06.jdbc.mysql.sql.row.insert;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190323";}


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
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String path = (String) o[0];
		Map map = (Map) o[1];
		
		List keys = new ArrayList(map.keySet());
		Collections.sort(keys);
		int nb = keys.size();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO "+formatName.t(path)+" (");
		for(int i=0;i<nb;i++)
		{
			String key = (String) keys.get(i);
			sql.append(formatName.t(key));
			if(i<nb-1) sql.append(",");
		}
		sql.append(") VALUES (");
		for(int i=0;i<nb;i++)
		{
			String key = (String) keys.get(i);
			Object value = map.get(key);
			sql.append(formatValue.t(value));
			if(i<nb-1) sql.append(",");
		}
		sql.append(")");
		
		return sql.toString();
	}
}
