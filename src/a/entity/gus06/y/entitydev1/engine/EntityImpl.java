package a.entity.gus06.y.entitydev1.engine;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251110";}

	private Service retrieveDep;

	public EntityImpl() throws Exception
	{
		retrieveDep = Outside.service(this,"gus06.y.entitydev1.retrieve.dependencies");
	}
	
	public Object t(Object obj) throws Exception
	{return new Handler((File) obj);}
	
	private class Handler implements T
	{
		private File rootDir;
		public Handler(File rootDir) {this.rootDir = rootDir;}
		
		public Object t(Object obj) throws Exception
		{
			String entityName = (String) obj;
			return retrieveDep.t(new Object[]{rootDir, entityName});
		}
	}
}