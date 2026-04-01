package a.entity.gus06.jdbc.mysql.perform.table.findprimarykey.strict;

import a.framework.*;
import java.util.Set;
import java.sql.Connection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170516";}


	private Service find;

	public EntityImpl() throws Exception
	{
		find = Outside.service(this,"gus06.jdbc.mysql.perform.table.findprimarykeys");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] t = (Object[]) obj;
		if(t.length!=2) throw new Exception("Wrong data number: "+t.length);
		
		Connection cx = (Connection) t[0];
		String path = (String) t[1];
		
		Set set = (Set) find.t(obj);
		
		int nb = set.size();
		if(nb==0) throw new Exception("No primary key found for table: "+path);
		if(nb>1) throw new Exception("Many primary keys found for table: "+path);
		
		return set.iterator().next();
	}
}
