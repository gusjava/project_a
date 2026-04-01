package a.entity.gus06.jdbc.mysql.perform.db.empty;

import a.framework.*;
import java.sql.Connection;
import java.util.Set;
import java.util.Iterator;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160409";}


	private Service protectedPath;
	private Service tableSet;
	private Service dropTable;
	private Service clearTable;


	public EntityImpl() throws Exception
	{
		protectedPath = Outside.service(this,"gus06.jdbc.mysql.check.protectedpath");
		tableSet = Outside.service(this,"gus06.jdbc.mysql.perform.find.tableset.db");
		dropTable = Outside.service(this,"gus06.jdbc.mysql.perform.table.drop");
		clearTable = Outside.service(this,"gus06.jdbc.mysql.perform.fk.clear.table");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String dbName = (String) o[1];
		
		if(dbName==null) throw new Exception("Db name is null");
		if(protectedPath.f(dbName)) throw new Exception("Attempt to empty db: "+dbName);
		
		Set tables = (Set) tableSet.t(new Object[]{cx,dbName});
		
		Iterator it1 = tables.iterator();
		while(it1.hasNext())
		{
			String tableName = (String) it1.next();
			clearTable.p(new Object[]{cx,dbName,tableName});
		}
		
		Iterator it2 = tables.iterator();
		while(it2.hasNext())
		{
			String tableName = (String) it2.next();
			String path = dbName+"."+tableName;
			dropTable.p(new Object[]{cx,path});
		}
	}
}