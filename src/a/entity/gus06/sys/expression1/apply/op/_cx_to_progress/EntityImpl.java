package a.entity.gus06.sys.expression1.apply.op._cx_to_progress;

import a.framework.*;
import java.sql.Connection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170209";}


	private Service getWatcher;
	private Service watcherToProgress;
	
	public EntityImpl() throws Exception
	{
		getWatcher = Outside.service(this,"gus06.jdbc.connection.builder.watcher");
		watcherToProgress = Outside.service(this,"gus06.mysql.socketfactory.watcher.progress2");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof Connection)
		{
			Object watcher = getWatcher.t(obj);
			if(watcher==null) return null;
			return watcherToProgress.t(watcher);
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
