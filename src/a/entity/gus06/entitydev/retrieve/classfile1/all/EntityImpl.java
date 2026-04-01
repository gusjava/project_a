package a.entity.gus06.entitydev.retrieve.classfile1.all;

import a.framework.*;
import java.io.File;
import java.io.FileFilter;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140731";}

	private FileFilter filter = new FileFilter() {
		public boolean accept(File f)
		{return f.isFile() && f.getName().endsWith(".class");}
	};


	private Service retrieve;

	public EntityImpl() throws Exception
	{retrieve = Outside.service(this,"gus06.entitydev.retrieve.classfile1");}
	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) retrieve.t(obj);
		if(!file.isFile()) throw new Exception("File not found: "+file);
		return file.getParentFile().listFiles(filter);
	}
}
