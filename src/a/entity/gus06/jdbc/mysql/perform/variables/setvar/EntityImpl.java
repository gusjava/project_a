package a.entity.gus06.jdbc.mysql.perform.variables.setvar;

import a.framework.*;
import java.sql.Connection;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170129";}


	private Service buildSql;
	private Service executeSql;


	public EntityImpl() throws Exception
	{
		buildSql = Outside.service(this,"gus06.jdbc.mysql.sql.variables.setvar");
		executeSql = Outside.service(this,"gus06.jdbc.mysql.perform.sqlexecute");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String key = (String) o[1];
		String value = (String) o[2];
		
		String sql = (String) buildSql.t(new String[]{key,value});
		executeSql.p(new Object[]{cx,sql});
	}
}
