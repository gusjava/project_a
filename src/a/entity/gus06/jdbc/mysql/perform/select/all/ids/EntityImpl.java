package a.entity.gus06.jdbc.mysql.perform.select.all.ids;

import a.framework.*;
import java.sql.Connection;
import java.util.Collection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170523";}
	
	public static final String ID = "id";


	private Service buildSql;
	private Service executeSql;
	private Service formatSeq;


	public EntityImpl() throws Exception
	{
		buildSql = Outside.service(this,"gus06.jdbc.mysql.sql.select.all.where");
		executeSql = Outside.service(this,"gus06.jdbc.mysql.perform.sqlexecute");
		formatSeq = Outside.service(this,"gus06.jdbc.mysql.format.sql.sequence");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String path = (String) o[1];
		Collection ids = (Collection) o[2];
		
		if(ids.isEmpty()) return null;
		
		String where = ID+" in ("+formatSeq.t(ids)+")";
		String sql = (String) buildSql.t(new String[]{path,where});
		return executeSql.t(new Object[]{cx,sql});
	}
}
