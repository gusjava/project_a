package a.entity.gus06.jdbc.gui.cx1.db.list.control.rename;

import a.framework.*;
import java.io.File;
import java.util.List;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20231014";}

	private Service renameDb;
	
	public EntityImpl() throws Exception
	{
		renameDb = Outside.service(this,"gus06.jdbc.mysql.perform.db.rename.ask");
	}
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object cx = o[0];
		List selection = (List) o[1];
		
		boolean done = false;
		for(int i=0;i<selection.size();i++)
		{
			String dbName = (String) selection.get(i);
			if(renameDb.f(new Object[]{cx,dbName})) done = true;
		}
		return done;
	}
}