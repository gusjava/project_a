package a.entity.gus06.jdbc.mysql.perform.sqlexecute.multi;

import a.framework.*;
import java.sql.Connection;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170801";}


	private Service executeSql;

	public EntityImpl() throws Exception
	{
		executeSql = Outside.service(this,"gus06.jdbc.mysql.perform.sqlexecute");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		Object sql = o[1];
		
		if(sql instanceof String)
		{
			executeLine(cx,(String) sql);
			return;
		}
		if(sql instanceof String[])
		{
			String[] lines = (String[]) sql;
			for(String line : lines) executeLine(cx,line);
			return;
		}
		if(sql instanceof List)
		{
			List lines = (List) sql;
			for(Object line : lines) executeLine(cx,(String) line);
			return;
		}
		throw new Exception("Invalid sql data type: "+sql.getClass().getName());
	}
	
	
	
	private void executeLine(Connection cx, String line) throws Exception
	{executeSql.p(new Object[]{cx,line});}
}
