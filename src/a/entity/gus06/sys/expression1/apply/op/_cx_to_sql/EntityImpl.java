package a.entity.gus06.sys.expression1.apply.op._cx_to_sql;

import a.framework.*;
import java.sql.Connection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170330";}


	private Service getSQL;
	
	public EntityImpl() throws Exception
	{
		getSQL = Outside.service(this,"gus06.jdbc.connection.sqlrecorder");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof Connection) return getSQL.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
