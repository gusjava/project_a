package a.entity.gus06.jdbc.mysql.perform.user.create;

import java.sql.Connection;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150624";}

	
	private Service buildSql;
	private Service executeSql;
	
	public EntityImpl() throws Exception
	{
		buildSql = Outside.service(this,"gus06.jdbc.mysql.sql.user.create");
		executeSql = Outside.service(this,"gus06.jdbc.mysql.perform.sqlexecute");
	}
	
	

	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String user = (String) o[1];
		String host = (String) o[2];
		String pwd = (String) o[3];
		
		String sql = (String) buildSql.t(new String[]{user,host,pwd});
		System.out.println("sql: "+sql);
		executeSql.p(new Object[]{cx,sql});
	}
}
