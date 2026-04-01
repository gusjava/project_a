package a.entity.gus06.sys.jdbcmap2.perform.entry.insertupdate.id;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150809";}

	
	private Service buildSql;
	private Service performDelete;
	

	public EntityImpl() throws Exception
	{
		buildSql = Outside.service(this,"gus06.sys.jdbcmap2.sql.entry.insertupdate.id");
		performDelete = Outside.service(this,"gus06.sys.jdbcmap2.perform.entry.delete.id");
	}



	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String path = (String) o[1];
		String id = (String) o[2];
		Map map = (Map) o[3];
		
		if(map==null)
		{
			performDelete.p(new Object[]{cx,path,id});
			return;
		}
		
		String sql = (String) buildSql.t(new Object[]{path,id,map});
		
		Statement st = cx.createStatement();
		int res = st.executeUpdate(sql);
		st.close();
	}
}
