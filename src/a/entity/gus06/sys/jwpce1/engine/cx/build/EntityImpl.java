package a.entity.gus06.sys.jwpce1.engine.cx.build;

import a.framework.*;
import java.io.File;
import java.sql.Connection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250721";}

	private Service buildCx;
	private Service checkDb;
	private Service initDb;
	
	public EntityImpl() throws Exception
	{
		buildCx = Outside.service(this,"gus06.y.api2.sqlite.cx.build");
		checkDb = Outside.service(this,"gus.y.sqlitecache1.check");
		initDb = Outside.service(this,"gus06.sys.jwpce1.engine.cx.initdb");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		return new Holder((File) obj);
	}
	
	private class Holder implements G
	{
		private File file;
		private Connection cx;
		
		public Holder(File file) throws Exception
		{
			this.file = file;
		}
		
		public Object g() throws Exception
		{
			if (cx == null || cx.isClosed()) init();
			return cx;
		}

		private void init() throws Exception
		{
			cx = (Connection) buildCx.t(file);
			checkDb.p(new Object[] { cx, initDb });
		}
	}
}