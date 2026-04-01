package a.entity.gus06.dir.contains.filetof;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170411";}

	
	
	public Object t(Object obj) throws Exception
	{
		return new F1((File) obj);
	}
	
	
	private class F1 implements F
	{
		private File dir;
		
		public F1(File dir) throws Exception
		{
			this.dir = dir;
			if(!dir.isDirectory()) throw new Exception("Path is not a directory: "+dir);
		}
		
		public boolean f(Object obj) throws Exception
		{
			if(obj instanceof String) return toFile(dir,(String) obj).exists();
			
			throw new Exception("Invalid data type: "+obj.getClass().getName());
		}
	}
	
	
	private File toFile(File dir, String s)
	{return new File(dir,s);}
}
