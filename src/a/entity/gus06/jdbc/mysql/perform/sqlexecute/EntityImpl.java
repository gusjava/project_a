package a.entity.gus06.jdbc.mysql.perform.sqlexecute;

import a.framework.*;
import java.sql.Connection;
import java.sql.Statement;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20141009";}

	private Service recorder;

	public EntityImpl() throws Exception
	{
		recorder = Outside.service(this,"gus06.jdbc.connection.sqlrecorder");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String sql = (String) o[1];
		
		try
		{
			Statement st = cx.createStatement();
			st.execute(sql);
			st.close();
			
			recorder.v(sql,cx);
		}
		catch(Exception e)
		{
			String message = "SQL execution failed: ["+sql+"]";
			throw new Exception(message,e);
		}
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String sql = (String) o[1];
		
		try
		{
			Statement st = cx.createStatement();
			if(isReturnSql(sql)) return st.executeQuery(sql);
		
			Integer n = Integer.valueOf(st.executeUpdate(sql));
			st.close();
			return n;
		}
		catch(Exception e)
		{
			String message = "SQL execution failed: ["+sql+"]";
			throw new Exception(message,e);
		}
	}
	
	private boolean isReturnSql(String s)
	{
		s = s.toLowerCase();
		return s.startsWith("select ") || s.startsWith("show ") || s.startsWith("describe ");
	}
}
