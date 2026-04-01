package a.entity.gus06.sys.jdbcmap1.perform.entry.delete.id;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150808";}

	
	private Service buildSql;
	

	public EntityImpl() throws Exception
	{
		buildSql = Outside.service(this,"gus06.sys.jdbcmap1.sql.entry.delete.id");
	}


	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String path = (String) o[1];
		String id = (String) o[2];
		
		
		String sql = (String) buildSql.t(new String[]{path,id});
		
		Statement st = cx.createStatement();
		int res = st.executeUpdate(sql);
		st.close();
	}
}
