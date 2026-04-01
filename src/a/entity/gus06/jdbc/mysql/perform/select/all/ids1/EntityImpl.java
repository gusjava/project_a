package a.entity.gus06.jdbc.mysql.perform.select.all.ids1;

import a.framework.*;
import java.sql.Connection;
import java.util.Collection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170522";}
	


	private Service perform;
	private Service findPrimaryKey;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.jdbc.mysql.perform.select.all.ids1.perform");
		findPrimaryKey = Outside.service(this,"gus06.jdbc.mysql.perform.table.findprimarykey.strict");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String path = (String) o[1];
		Collection ids = (Collection) o[2];
		
		if(ids.isEmpty()) return null;
		
		String idCol = (String) findPrimaryKey.t(new Object[]{cx,path});
		return perform.t(new Object[]{cx,path,ids,idCol});
	}
}
