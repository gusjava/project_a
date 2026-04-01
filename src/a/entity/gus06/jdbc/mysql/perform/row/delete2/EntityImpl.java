package a.entity.gus06.jdbc.mysql.perform.row.delete2;

import a.framework.*;
import java.sql.Connection;
import java.util.Map;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160406";}


	private Service deleteRow;
	private Service findPrimary;
	
	public EntityImpl() throws Exception
	{
		deleteRow = Outside.service(this,"gus06.jdbc.mysql.perform.row.delete");
		findPrimary = Outside.service(this,"gus06.jdbc.mysql.perform.table.findprimarykeys");
	}


	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String path = (String) o[1];
		Object ids = o[2];
		
		Set pkeys = (Set) findPrimary.t(new Object[]{cx,path});
		deleteRow.p(new Object[]{cx,path,ids,pkeys});
	}
}
