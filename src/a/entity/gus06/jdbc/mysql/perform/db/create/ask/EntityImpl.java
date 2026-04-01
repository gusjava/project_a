package a.entity.gus06.jdbc.mysql.perform.db.create.ask;

import a.framework.*;
import java.sql.Connection;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20150625";}
	
	public static final String TITLE = "Create database";


	private Service create;
	private Service dialog;


	public EntityImpl() throws Exception
	{
		create = Outside.service(this,"gus06.jdbc.mysql.perform.db.create");
		dialog = Outside.service(this,"gus06.input.text.dialog");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		
		String newName = (String) dialog.t(TITLE);
		if(newName==null || newName.equals("")) return false;
		
		create.p(new Object[]{cx,newName});
		return true;
	}
}
