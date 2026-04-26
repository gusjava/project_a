package a.entity.gus06.file.contains.filetof;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231019";}


	private Service readProp;

	public EntityImpl() throws Exception
	{
		readProp = Outside.service(this,"gus.x.file.prop.read");
	}
	
	public Object t(Object obj) throws Exception
	{
		return new F1((File) obj);
	}
	
	private class F1 implements F
	{
		private File file;
		private Map map;
		
		public F1(File file) throws Exception
		{
			this.file = file;
			if(!file.isFile()) throw new Exception("Path is not a file: "+file);
			map = (Map) readProp.t(file);
		}
		
		public boolean f(Object obj) throws Exception
		{
			if(obj instanceof String) return map.containsKey(obj);
			throw new Exception("Invalid data type: "+obj.getClass().getName());
		}
	}
}
