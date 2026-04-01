package a.entity.gus06.jdbc.mysql.perform.db.rename.ask;

import a.framework.*;
import java.sql.Connection;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20150625";}
	
	public static final String TITLE = "Rename database";


	private Service rename;
	private Service dialog;


	public EntityImpl() throws Exception
	{
		rename = Outside.service(this,"gus06.jdbc.mysql.perform.db.rename");
		dialog = Outside.service(this,"gus06.input.text.dialog");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String oldName = (String) o[1];
		
		String newName = (String) dialog.t(new String[]{TITLE,oldName});
		if(newName==null || newName.equals("") || newName.equals(oldName)) return false;
		
		rename.p(new Object[]{cx,oldName,newName});
		return true;
	}
}
