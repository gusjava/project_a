package a.entity.gus06.jdbc.mysql.perform.fk.add;

import a.framework.*;
import java.sql.Connection;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170321";}


	private Service buildSql;
	private Service executeSql;
	private Service protectedPath;

	public EntityImpl() throws Exception
	{
		buildSql = Outside.service(this,"gus06.jdbc.mysql.sql.foreignkey.add");
		executeSql = Outside.service(this,"gus06.jdbc.mysql.perform.sqlexecute");
		protectedPath = Outside.service(this,"gus06.jdbc.mysql.check.protectedpath");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=6) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String path = (String) o[1];
		String fkName = (String) o[2];
		String column1 = (String) o[3];
		String refTable = (String) o[4];
		String column2 = (String) o[5];
		
		if(protectedPath.f(path)) throw new Exception("Attempt to alter table: "+path);
		
		String sql = (String) buildSql.t(new Object[]{path,fkName,column1,refTable,column2});
		executeSql.p(new Object[]{cx,sql});
	}
}