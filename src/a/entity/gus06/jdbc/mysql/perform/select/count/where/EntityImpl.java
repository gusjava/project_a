package a.entity.gus06.jdbc.mysql.perform.select.count.where;

import a.framework.*;
import java.sql.Connection;
import java.sql.ResultSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161008";}


	private Service buildSql;
	private Service executeSql;
	private Service nextToInt;
	private Service buildWhere;


	public EntityImpl() throws Exception
	{
		buildSql = Outside.service(this,"gus06.jdbc.mysql.sql.select.count.where");
		executeSql = Outside.service(this,"gus06.jdbc.mysql.perform.sqlexecute");
		nextToInt = Outside.service(this,"gus06.jdbc.resultset.next.tointeger");
		buildWhere = Outside.service(this,"gus06.jdbc.mysql.sql.where");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		Object path = o[1];
		String where = (String) buildWhere.t(o[2]);
		
		String sql = (String) buildSql.t(new Object[]{path,where});
		ResultSet rs = (ResultSet) executeSql.t(new Object[]{cx,sql});
		return nextToInt.t(rs);
	}
}