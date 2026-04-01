package a.entity.gus06.jdbc.mysql.perform.variables.findkey;

import a.framework.*;
import java.sql.ResultSet;
import java.sql.Connection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170129";}
	

	private Service perform;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.jdbc.mysql.perform.variables.show");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String key = (String) o[1];
		
		
		ResultSet rs = (ResultSet) perform.t(cx);
		
		Object value = null;
		while(rs.next() && value==null)
		{
			String key1 = rs.getString(1);
			if(key1.equals(key)) value = rs.getObject(2);
		}
		rs.close();
		return value;
	}
}
