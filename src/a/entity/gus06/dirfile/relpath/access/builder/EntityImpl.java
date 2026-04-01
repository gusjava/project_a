package a.entity.gus06.dirfile.relpath.access.builder;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191125";}
	
	
	public Object t(Object obj) throws Exception
	{return new Access((File) obj);}
	
	
	private class Access implements T, R
	{
		private File root;
		public Access(File root) throws Exception
		{
			this.root = root;
			if(!root.isDirectory()) throw new Exception("Root not found");
		}
		
		public Object t(Object obj) throws Exception
		{return r((String) obj);}
		
		public Object r(String key) throws Exception
		{return new File(root,key);}
	}
}