package a.entity.gus06.sys.taskmanager1.engine;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.Map;
import java.sql.Connection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250819";}


	private Service buildCx;
	private Service saveTask;

	public EntityImpl() throws Exception
	{
		buildCx = Outside.service(this,"gus06.sys.taskmanager1.engine.cx.build");
		saveTask = Outside.service(this,"gus06.sys.taskmanager1.engine.perform.task.save");
	}
	
	public Object t(Object obj) throws Exception
	{
		return new Holder((File) obj);
	}
	
	private class Holder implements V, R
	{
		private File dbFile;
		private G getCx;
		
		public Holder(File dbFile) throws Exception
		{
			this.dbFile = dbFile;
			getCx = (G) buildCx.t(dbFile);
		}
		
		public void v(String key, Object obj) throws Exception
		{
			if(key.equals("saveTask")) {saveTask((Map) obj);return;}
			
			throw new Exception("Unknown key: "+key);
		}
		
		public Object r(String key) throws Exception
		{
			if(key.equals("dbFile")) return dbFile;
			if(key.equals("getCx")) return getCx;
			
			if(key.equals("keys")) return new String[]{
				"dbFile","getCx"};
			throw new Exception("Unknown key: "+key);
		}
		
		private void saveTask(Map data) throws Exception
		{
			Connection cx = (Connection) getCx.g();
			saveTask.p(new Object[]{cx, data});
		}
	}
}