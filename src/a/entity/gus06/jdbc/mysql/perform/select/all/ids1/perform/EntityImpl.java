package a.entity.gus06.jdbc.mysql.perform.select.all.ids1.perform;

import a.framework.*;
import java.sql.Connection;
import java.util.Collection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170523";}
	


	private Service formatSeq;
	private Service buildSql;
	private Service executeSql;

	public EntityImpl() throws Exception
	{
		formatSeq = Outside.service(this,"gus06.jdbc.mysql.format.sql.sequence");
		buildSql = Outside.service(this,"gus06.jdbc.mysql.sql.select.all.where");
		executeSql = Outside.service(this,"gus06.jdbc.mysql.perform.sqlexecute");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String path = (String) o[1];
		Collection ids = (Collection) o[2];
		String idCol = (String) o[3];
		
		String where = idCol+" in ("+formatSeq.t(ids)+")";
		String sql = (String) buildSql.t(new String[]{path,where});
		return executeSql.t(new Object[]{cx,sql});
	}
}
