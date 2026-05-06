package a.entity.gus06.y.entitydb1.buildcx;

import a.framework.*;
import java.io.File;
import java.sql.Connection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251111";}


	private Service buildCx;
	private Service checkDb;
	private Service initDb;

	public EntityImpl() throws Exception
	{
		buildCx = Outside.service(this,"gus06.y.api2.h2.cx.build");
		checkDb = Outside.service(this,"gus.y.h2cache1.check");
		initDb = Outside.service(this, "gus.y.entitydb1.cx.initdb");
	}
	
	
	public Object t(Object obj) throws Exception
	{return new Builder((File) obj);}
	
	
	private class Builder implements G
	{
		private File dbFile;
		private Connection cx;
		
		public Builder(File dbFile)
		{
			this.dbFile = dbFile;
		}

		public Object g() throws Exception
		{
			if (cx == null || cx.isClosed()) init();
			return cx;
		}

		private void init() throws Exception
		{
			cx = (Connection) buildCx.t(dbFile);
			checkDb.p(new Object[] { cx, initDb });
		}
	}
}