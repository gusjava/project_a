package a.entity.gus06.sys.taskmanager1.engine.perform.task.save;

import a.framework.*;
import java.sql.Connection;
import java.util.Map;
import java.util.Date;
import java.text.SimpleDateFormat;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250820";}

	public static final String COL_ID = "id";

	private Service insert;
	private Service update;

	public EntityImpl() throws Exception
	{
		insert = Outside.service(this,"gus06.sys.taskmanager1.engine.perform.task.insert");
		update = Outside.service(this,"gus06.sys.taskmanager1.engine.perform.task.update");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		Map data = (Map) o[1];
		
		if(data.containsKey(COL_ID)) update.p(obj);
		else insert.p(obj);
	}
}