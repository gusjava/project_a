package a.entity.gus06.sys.expression1.apply.op._jdbc_c_tableset;

import a.framework.*;
import java.sql.Connection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231023";}

	private Service perform;
	private Service dbName;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.jdbc.generic.perform.find.tableset.db");
		dbName = Outside.service(this,"gus06.jdbc.generic.perform.cx.dbname");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof Connection) return perform((Connection) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	public Object perform(Connection cx) throws Exception
	{
		String name = (String) dbName.t(cx);
		return perform.t(new Object[]{cx,name});
	}
}