package a.entity.gus06.jdbc.mysql.perform.db.rename;

import java.sql.Connection;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150625";}


	private Service createDb;
	private Service moveDb;
	private Service dropDb;

	public EntityImpl() throws Exception
	{
		createDb = Outside.service(this,"gus06.jdbc.mysql.perform.db.create");
		moveDb = Outside.service(this,"gus06.jdbc.mysql.perform.db.movetables");
		dropDb = Outside.service(this,"gus06.jdbc.mysql.perform.db.drop");
	}




	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String oldName = (String) o[1];
		String newName = (String) o[2];
		
		createDb.p(new Object[]{cx,newName});
		moveDb.p(new Object[]{cx,oldName,newName});
		dropDb.p(new Object[]{cx,oldName});
	}
}
