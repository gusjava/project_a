package a.entity.gus06.jdbc.mysql.perform.row.delete;

import a.framework.*;
import java.sql.Connection;
import java.util.Map;
import java.util.Set;
import java.sql.PreparedStatement;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160406";}


	private Service format;
	
	public EntityImpl() throws Exception
	{format = Outside.service(this,"gus06.jdbc.mysql.format.sql.name");}


	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String path = (String) o[1];
		Object ids = o[2];
		Set pkeys = (Set) o[3];
		
		Map mapPK = buildMapPK(pkeys,ids);
		
		List keys = new ArrayList(mapPK.keySet());
		Collections.sort(keys);
		int nb = keys.size();
		
		
		
		StringBuilder sql = new StringBuilder();
		List params = new ArrayList();
		
		sql.append("DELETE FROM "+format(path)+" WHERE ");
		for(int i=0;i<nb;i++)
		{
			String key = (String) keys.get(i);
			Object value = mapPK.get(key);
			
			sql.append(" "+format(key)+"=?");
			if(i<nb-1) sql.append(" AND ");
			
			params.add(value);
		}
		
		PreparedStatement st = cx.prepareStatement(sql.toString());
		
		for(int i=0;i<params.size();i++)
		{
			Object param = params.get(i);
			st.setObject(i+1,param);
		}
		
		st.executeUpdate();
	}
	
	private String format(String s) throws Exception
	{return (String) format.t(s);}
	
	
	
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
