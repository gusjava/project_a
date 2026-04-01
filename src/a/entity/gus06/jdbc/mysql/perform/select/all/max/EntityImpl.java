package a.entity.gus06.jdbc.mysql.perform.select.all.max;

import a.framework.*;
import java.sql.Connection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231112";}


	private Service buildSql;
	private Service executeSql;


	public EntityImpl() throws Exception
	{
		buildSql = Outside.service(this,"gus06.jdbc.mysql.sql.select.all.max");
		executeSql = Outside.service(this,"gus06.jdbc.mysql.perform.sqlexecute");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String path = (String) o[1];
		String colName = (String) o[2];
		
		String sql = (String) buildSql.t(new String[]{path,colName});
		return executeSql.t(new Object[]{cx,sql});
	}
}