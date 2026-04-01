package a.entity.gus06.jdbc.mysql.perform.fk.clear.table;

import java.sql.Connection;
import java.util.Set;
import java.util.Iterator;
import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170322";}


	private Service fkList;
	private Service fkDrop;

	public EntityImpl() throws Exception
	{
		fkList = Outside.service(this,"gus06.jdbc.mysql.perform.fk.name.table");
		fkDrop = Outside.service(this,"gus06.jdbc.mysql.perform.fk.drop");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String dbName = (String) o[1];
		String tableName = (String) o[2];
		
		String path = dbName+"."+tableName;
		
		List list = (List) fkList.t(new Object[]{cx,dbName,tableName});
		for(int i=0;i<list.size();i++)
		{
			String fkName = (String) list.get(i);
			fkDrop.p(new Object[]{cx,path,fkName});
		}
	}
}