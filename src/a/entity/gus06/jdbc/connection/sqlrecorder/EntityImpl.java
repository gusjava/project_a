package a.entity.gus06.jdbc.connection.sqlrecorder;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.sql.Connection;

public class EntityImpl implements Entity, V, T {

	public String creationDate() {return "20170330";}

	private Map map;

	public EntityImpl() throws Exception
	{
		map = new HashMap();
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(!map.containsKey(obj)) return null;
		return map.get(obj).toString();
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		String sql = key;
		
		if(!sql.endsWith(";")) sql = sql+";";
		
		if(!map.containsKey(cx))
		map.put(cx,new StringBuffer());
		
		StringBuffer sb = (StringBuffer) map.get(cx);
		sb.append(sql+"\n\n");
	}
}
