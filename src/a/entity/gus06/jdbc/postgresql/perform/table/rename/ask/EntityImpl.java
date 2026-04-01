package a.entity.gus06.jdbc.postgresql.perform.table.rename.ask;

import a.framework.*;
import java.sql.Connection;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20190719";}
	
	public static final String TITLE = "Rename table";


	private Service rename;
	private Service dialog;


	public EntityImpl() throws Exception
	{
		rename = Outside.service(this,"gus06.jdbc.postgresql.perform.table.rename");
		dialog = Outside.service(this,"gus06.input.text.dialog");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String dbName = (String) o[1];
		String oldName = (String) o[2];
		
		String newName = (String) dialog.t(new String[]{TITLE,oldName});
		if(newName==null || newName.equals("") || newName.equals(oldName)) return false;
		
		String path1 = dbName+"."+oldName;
		String path2 = dbName+"."+newName;
		
		rename.p(new Object[]{cx,path1,path2});
		return true;
	}
}
