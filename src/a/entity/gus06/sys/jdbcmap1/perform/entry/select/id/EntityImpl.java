package a.entity.gus06.sys.jdbcmap1.perform.entry.select.id;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150804";}
	
	public final static String PRIMARY_KEY = "ID";

	
	private Service buildSql;
	private Service nextToMap;
	

	public EntityImpl() throws Exception
	{
		buildSql = Outside.service(this,"gus06.sys.jdbcmap1.sql.entry.select.id");
		nextToMap = Outside.service(this,"gus06.jdbc.resultset.next.tostringmap");
	}


	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String path = (String) o[1];
		String id = (String) o[2];
		
		
		String sql = (String) buildSql.t(new String[]{path,id});
		
		Statement st = cx.createStatement();
		ResultSet rs = st.executeQuery(sql);
		Map map = (Map) nextToMap.t(rs);
		st.close();
		
		map.remove(PRIMARY_KEY);
		return map;
	}
}
