package a.entity.gus06.sys.expression1.apply.op._jdbc_dbname;

import a.framework.*;
import java.sql.Connection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231017";}

	private Service sqlQuery;
	
	public EntityImpl() throws Exception
	{
		sqlQuery = Outside.service(this,"gus06.jdbc.generic.perform.cx.dbname");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof Connection) return sqlQuery.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}