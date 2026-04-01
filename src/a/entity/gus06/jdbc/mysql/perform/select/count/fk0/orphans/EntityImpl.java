package a.entity.gus06.jdbc.mysql.perform.select.count.fk0.orphans;

import a.framework.*;
import java.sql.Connection;
import java.sql.ResultSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20230304";}


	private Service buildSql;
	private Service executeSql;
	private Service nextToInt;


	public EntityImpl() throws Exception
	{
		buildSql = Outside.service(this,"gus06.jdbc.mysql.sql.select.count.fk0.orphans");
		executeSql = Outside.service(this,"gus06.jdbc.mysql.perform.sqlexecute");
		nextToInt = Outside.service(this,"gus06.jdbc.resultset.next.tointeger");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=6) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String dbName = (String) o[1];
		String tableName = (String) o[2];
		String colName = (String) o[3];
		String refTable = (String) o[4];
		String refCol = (String) o[5];
		
		String sql = (String) buildSql.t(new String[]{dbName,tableName,colName,refTable,refCol});
		ResultSet rs = (ResultSet) executeSql.t(new Object[]{cx,sql});
		return nextToInt.t(rs);
	}
}