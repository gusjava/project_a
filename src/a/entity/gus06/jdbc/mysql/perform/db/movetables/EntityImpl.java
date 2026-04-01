package a.entity.gus06.jdbc.mysql.perform.db.movetables;

import java.sql.Connection;
import java.util.Iterator;
import java.util.Set;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150625";}

	private Service renameTable;
	private Service selectTables;
	

	public EntityImpl() throws Exception
	{
		renameTable = Outside.service(this,"gus06.jdbc.mysql.perform.table.rename");
		selectTables = Outside.service(this,"gus06.jdbc.generic.perform.find.tableset.db");
	}



	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String dbName1 = (String) o[1];
		String dbName2 = (String) o[2];
		
		
		Set set1 = (Set) selectTables.t(new Object[]{cx,dbName1});
		Set set2 = (Set) selectTables.t(new Object[]{cx,dbName2});
		
		Iterator it = set1.iterator();
		while(it.hasNext())
		{
			String name = (String) it.next();
			if(!set2.contains(name))
			{
				String path1 = dbName1+"."+name;
				String path2 = dbName2+"."+name;
				moveTable(cx,path1,path2);
			}
		}
	}
	
	
	
	private void moveTable(Connection cx, String path1, String path2)
	{
		try{renameTable.p(new Object[]{cx,path1,path2});}
		catch(Exception e)
		{Outside.err(this,"moveTable(Connection,String,String)",e);}
	}
}
