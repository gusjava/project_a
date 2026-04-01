package a.entity.gus06.jdbc.mysql.perform.db.backup1;

import a.framework.*;
import java.sql.Connection;
import java.util.Iterator;
import java.util.Set;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160420";}


	private Service createDb;
	private Service selectTables;
	private Service duplicate;


	public EntityImpl() throws Exception
	{
		createDb = Outside.service(this,"gus06.jdbc.mysql.perform.db.create");
		selectTables = Outside.service(this,"gus06.jdbc.generic.perform.find.tableset.db");
		duplicate = Outside.service(this,"gus06.jdbc.mysql.perform.table.duplicate");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String dbName = (String) o[1];
		
		String backupName = "backup_"+dbName+"_"+now();
		Set tables = (Set) selectTables.t(new Object[]{cx,dbName});
		
		createDb.p(new Object[]{cx,backupName});
		
		Iterator it = tables.iterator();
		while(it.hasNext())
		{
			String tableName = (String) it.next();
			String path = dbName+"."+tableName;
			String path1 = backupName+"."+tableName;
			
			duplicate.p(new Object[]{cx,path,path1});
		}
	}
	
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
	private String now(){return sdf.format(new Date());}
}
