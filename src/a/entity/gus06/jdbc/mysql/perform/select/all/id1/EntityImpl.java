package a.entity.gus06.jdbc.mysql.perform.select.all.id1;

import a.framework.*;
import java.sql.Connection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170516";}
	


	private Service buildSql;
	private Service executeSql;
	private Service formatValue;
	private Service findPrimaryKey;


	public EntityImpl() throws Exception
	{
		buildSql = Outside.service(this,"gus06.jdbc.mysql.sql.select.all.where");
		executeSql = Outside.service(this,"gus06.jdbc.mysql.perform.sqlexecute");
		formatValue = Outside.service(this,"gus06.jdbc.mysql.format.sql.value");
		findPrimaryKey = Outside.service(this,"gus06.jdbc.mysql.perform.table.findprimarykey.strict");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String path = (String) o[1];
		String id = (String) o[2];
		
		String idCol = (String) findPrimaryKey.t(new Object[]{cx,path});
		String where = idCol+"="+formatValue.t(id);
		String sql = (String) buildSql.t(new String[]{path,where});
		return executeSql.t(new Object[]{cx,sql});
	}
}
