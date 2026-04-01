package a.entity.gus06.sys.jdbcmap2.perform.entry.select.id;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150809";}



	private Service buildSql;
	private Service rsToMap;
	

	public EntityImpl() throws Exception
	{
		buildSql = Outside.service(this,"gus06.sys.jdbcmap2.sql.entry.select.id");
		rsToMap = Outside.service(this,"gus06.jdbc.resultset.tostringmap");
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
		Map map = (Map) rsToMap.t(rs);
		st.close();
		
		return map;
	}
}
