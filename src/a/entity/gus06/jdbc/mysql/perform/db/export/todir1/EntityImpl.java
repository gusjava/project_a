package a.entity.gus06.jdbc.mysql.perform.db.export.todir1;

import a.framework.*;
import java.sql.Connection;
import java.util.Iterator;
import java.util.Set;
import java.io.File;
import java.util.List;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170518";}


	private Service selectTables;
	private Service selectAll;
	private Service writeData;
	private Service formatMap;


	public EntityImpl() throws Exception
	{
		selectTables = Outside.service(this,"gus06.jdbc.generic.perform.find.tableset.db");
		selectAll = Outside.service(this,"gus06.jdbc.mysql.perform.select.all.as.maplist");
		writeData = Outside.service(this,"gus06.dir.access.write.properties.randomid");
		formatMap = Outside.service(this,"gus06.map.build.stringmap1");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String dbName = (String) o[1];
		File dir = (File) o[2];
		
		Set tables = (Set) selectTables.t(new Object[]{cx,dbName});
		
		Iterator it = tables.iterator();
		while(it.hasNext())
		{
			String tableName = (String) it.next();
			String path = dbName+"."+tableName;
			List list = (List) selectAll.t(new Object[]{cx,path});
			
			storeToDir(dir,tableName,list);
		}
	}
	
	
	private void storeToDir(File dir, String tableName, List list) throws Exception
	{
		File d = new File(dir,tableName);
		d.mkdirs();
		
		for(int i=0;i<list.size();i++)
		{
			Map m = (Map) formatMap.t(list.get(i));
			writeData.p(new Object[]{d,m});
		}
	}
	
}
