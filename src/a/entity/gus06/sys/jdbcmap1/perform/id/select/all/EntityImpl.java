package a.entity.gus06.sys.jdbcmap1.perform.id.select.all;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Set;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150804";}

	
	private Service buildSql;
	private Service rsToSet;
	
	
	public EntityImpl() throws Exception
	{
		buildSql = Outside.service(this,"gus06.sys.jdbcmap1.sql.id.select.all");
		rsToSet = Outside.service(this,"gus06.jdbc.resultset.tostringset");
	}
	
	

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String path = (String) o[1];
		
		
		String sql = (String) buildSql.t(path);
		
		Statement st = cx.createStatement();
		ResultSet rs = st.executeQuery(sql);
		Set set = (Set) rsToSet.t(rs);
		st.close();
		
		return set;
	}
}
