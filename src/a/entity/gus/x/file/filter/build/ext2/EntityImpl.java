package a.entity.gus.x.file.filter.build.ext2;

import java.io.File;
import java.io.FileFilter;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20220410";}

	public EntityImpl() throws Exception{}
	
	public Object t(Object obj) throws Exception
	{return new FileFilter1(toExtArray(obj));}
		
	private String[] toExtArray(Object obj) throws Exception
	{
		if(obj instanceof String[]) return (String[]) obj;
		if(obj instanceof String)
		{
			String s = (String) obj;
			return s.toLowerCase().split(";");
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	public class FileFilter1 implements FileFilter
	{
		private String[] exts;
		public FileFilter1(String[] exts)
		{this.exts = exts;}
		
		public boolean accept(File f)
		{
			if(f==null) return false;
			if(!f.isFile()) return false;
			
			String name_ = f.getName().toLowerCase();
			for(String ext : exts) if(name_.endsWith(ext)) return true;
			return false;
		}
	}
}