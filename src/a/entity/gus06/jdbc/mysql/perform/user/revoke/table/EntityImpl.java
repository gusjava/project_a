package a.entity.gus06.jdbc.mysql.perform.user.revoke.table;

import java.sql.Connection;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150624";}

	
	private Service buildSql;
	private Service executeSql;
	
	public EntityImpl() throws Exception
	{
		buildSql = Outside.service(this,"gus06.jdbc.mysql.sql.user.revoke.table");
		executeSql = Outside.service(this,"gus06.jdbc.mysql.perform.sqlexecute");
	}
	

	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=6) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		Object priv = o[1];
		Object database = o[2];
		Object table = o[3];
		Object user = o[4];
		Object host = o[5];
		
		String sql = (String) buildSql.t(new Object[]{priv,database,table,user,host});
		executeSql.p(new Object[]{cx,sql});
	}
}
