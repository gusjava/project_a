package a.entity.gus06.jdbc.mysql.perform.db.drop.ask;

import a.framework.*;
import java.sql.Connection;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20150625";}
	
	public static final String TITLE = "Remove database";


	private Service drop;
	private Service dialog;


	public EntityImpl() throws Exception
	{
		drop = Outside.service(this,"gus06.jdbc.mysql.perform.db.drop");
		dialog = Outside.service(this,"gus06.input.confirm.dialog");
	}
	
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String name = (String) o[1];
		
		boolean ok = dialog.f(TITLE);
		if(!ok) return false;
		
		drop.p(new Object[]{cx,name});
		return true;
	}
}
