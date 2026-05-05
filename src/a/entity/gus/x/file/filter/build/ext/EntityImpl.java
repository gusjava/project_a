package a.entity.gus.x.file.filter.build.ext;

import java.io.File;
import java.io.FileFilter;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20220410";}

	public EntityImpl() throws Exception{}

	public Object t(Object obj) throws Exception
	{return new FileFilter1((String) obj);}
	
	public class FileFilter1 implements FileFilter
	{
		private String ext;
		public FileFilter1(String ext)
		{this.ext = ext.toLowerCase();}
		
		public boolean accept(File f)
		{
			if(f==null) return false;
			if(!f.isFile()) return false;
			
			return f.getName().toLowerCase().endsWith(ext);
		}
	}
}