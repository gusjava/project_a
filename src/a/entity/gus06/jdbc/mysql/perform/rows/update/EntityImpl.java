package a.entity.gus06.jdbc.mysql.perform.rows.update;

import a.framework.*;
import java.sql.Connection;
import java.util.Map;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20161019";}

	private Service format;
	
	public EntityImpl() throws Exception
	{format = Outside.service(this,"gus06.jdbc.mysql.format.sql.name");}


	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String path = (String) o[1];
		Map map = (Map) o[2];
		
		
		List keys = new ArrayList(map.keySet());
		Collections.sort(keys);
		int nb = keys.size();
		
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
