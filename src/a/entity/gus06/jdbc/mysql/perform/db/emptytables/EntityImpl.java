package a.entity.gus06.jdbc.mysql.perform.db.emptytables;

import a.framework.*;
import java.sql.Connection;
import java.util.Set;
import java.util.Iterator;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160409";}


	private Service protectedPath;
	private Service tableSet;
	private Service truncateTable;


	public EntityImpl() throws Exception
	{
		protectedPath = Outside.service(this,"gus06.jdbc.mysql.check.protectedpath");
		tableSet = Outside.service(this,"gus06.jdbc.generic.perform.find.tableset.db");
		truncateTable = Outside.service(this,"gus06.jdbc.mysql.perform.table.truncate");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String dbName = (String) o[1];
		
		if(protectedPath.f(dbName)) throw new Exception("Attempt to drop db: "+dbName);
		
		Set tables = (Set) tableSet.t(new Object[]{cx,dbName});
		Iterator it = tables.iterator();
		while(it.hasNext())
		{
			String table = (String) it.next();
			String path = dbName+"."+table;
			truncateTable.p(new Object[]{cx,path});
		}
	}
}
