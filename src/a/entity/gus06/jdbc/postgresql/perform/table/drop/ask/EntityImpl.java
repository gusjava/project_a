package a.entity.gus06.jdbc.postgresql.perform.table.drop.ask;

import a.framework.*;
import java.sql.Connection;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20190726";}
	
	public static final String TITLE = "Remove table";


	private Service drop;
	private Service dialog;


	public EntityImpl() throws Exception
	{
		drop = Outside.service(this,"gus06.jdbc.postgresql.perform.table.drop");
		dialog = Outside.service(this,"gus06.input.confirm.dialog");
	}
	
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String dbName = (String) o[1];
		String tableName = (String) o[2];
		
		boolean ok = dialog.f(TITLE);
		if(!ok) return false;
		
		String path = dbName+"."+tableName;
		drop.p(new Object[]{cx,path});
		return true;
	}
}
