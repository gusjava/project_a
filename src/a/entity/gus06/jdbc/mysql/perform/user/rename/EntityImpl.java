package a.entity.gus06.jdbc.mysql.perform.user.rename;

import java.sql.Connection;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150624";}


	private Service buildSql;
	private Service executeSql;
	
	public EntityImpl() throws Exception
	{
		buildSql = Outside.service(this,"gus06.jdbc.mysql.sql.user.rename");
		executeSql = Outside.service(this,"gus06.jdbc.mysql.perform.sqlexecute");
	}



	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=5) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String user1 = (String) o[1];
		String host1 = (String) o[2];
		String user2 = (String) o[3];
		String host2 = (String) o[4];
		
		if(user1.equals("root")) throw new Exception("Attempt to rename user: "+user1);
		
		String sql = (String) buildSql.t(new String[]{user1,host1,user2,host2});
		executeSql.p(new Object[]{cx,sql});
	}
}
