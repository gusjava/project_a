package a.entity.gus06.jdbc.h2.perform.row.insert;

import a.framework.*;
import java.sql.Connection;
import java.util.Map;
import java.util.Set;
import java.sql.PreparedStatement;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.sql.Statement;
import java.sql.ResultSet;

public class EntityImpl implements Entity, P, T, F {

	public String creationDate() {return "20260109";}

	private Service format;
	private Service toMap;
	
	public EntityImpl() throws Exception
	{
		format = Outside.service(this,"gus06.jdbc.h2.format.sql.name");
		toMap = Outside.service(this,"gus06.find.stringmap");
	}

	public void p(Object obj) throws Exception
	{t(obj);}
	
	public boolean f(Object obj) throws Exception
	{return t(obj)!=null;}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String path = (String) o[1];
		Map map = (Map) toMap.t(o[2]);
		
		List keys = new ArrayList(map.keySet());
		Collections.sort(keys);
		int nb = keys.size();
		
		StringBuilder sql = new StringBuilder();
		List params = new ArrayList();
		
		sql.append("INSERT INTO "+format(path)+" (");
		for(int i=0;i<nb;i++)
		{
			String key = (String) keys.get(i);
			sql.append(format(key));
			if(i<nb-1) sql.append(",");
		}
		
		sql.append(") VALUES (");
		for(int i=0;i<nb;i++)
		{
			String key = (String) keys.get(i);
			Object value = map.get(key);
			sql.append("?");
			if(i<nb-1) sql.append(",");
			params.add(value);
		}
		
		sql.append(")");
		
		PreparedStatement st = cx.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
		
		for(int i=0;i<params.size();i++)
		{
			Object param = params.get(i);
			st.setObject(i+1,param);
		}
		
		int result = st.executeUpdate();
		if(result==0) return null;
		
		if(result>1) throw new Exception("Many row were affected when executing insert query: "+result+" (sql="+sql+")");
		
		ResultSet rs = st.getGeneratedKeys();
		if(!rs.next()) return null;
		
		return rs.getObject(1);
	}
	
	private String format(String s) throws Exception
	{return (String) format.t(s);}
}
