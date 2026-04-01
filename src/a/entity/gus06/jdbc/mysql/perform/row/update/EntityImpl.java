package a.entity.gus06.jdbc.mysql.perform.row.update;

import a.framework.*;
import java.sql.Connection;
import java.util.Map;
import java.util.Set;
import java.sql.PreparedStatement;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160405";}

	private Service format;
	
	public EntityImpl() throws Exception
	{format = Outside.service(this,"gus06.jdbc.mysql.format.sql.name");}


	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String path = (String) o[1];
		Map map = (Map) o[2];
		Set pkeys = (Set) o[3];
		
		
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
		
		if(nb==0) throw new Exception("Empty UPDATE detected");
		if(nb1==0) throw new Exception("Empty WHERE detected");
		
		StringBuilder sql = new StringBuilder();
		List params = new ArrayList();
		
		sql.append("UPDATE "+format(path)+" SET");
		for(int i=0;i<nb;i++)
		{
			String key = (String) keys.get(i);
			Object value = map.get(key);
			
			sql.append(" "+format(key)+"=?");
			if(i<nb-1) sql.append(",");
			
			params.add(value);
		}
		
		sql.append(" WHERE ");
		for(int i=0;i<nb1;i++)
		{
			String key1 = (String) keys1.get(i);
			Object value = map.get(key1);
			
			sql.append(" "+format(key1)+"=?");
			if(i<nb1-1) sql.append(" AND ");
			
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
}
