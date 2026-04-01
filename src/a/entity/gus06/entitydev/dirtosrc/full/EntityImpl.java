package a.entity.gus06.entitydev.dirtosrc.full;

import a.framework.*;
import java.io.File;
import java.io.FileFilter;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150301";}
	
	public static final String MAINNAME = "EntityImpl.java";


	private Service readFile;

	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus06.file.read.string");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		File mainFile = new File(dir,MAINNAME);
		
		if(!mainFile.exists())
			throw new Exception("Main file not found: "+mainFile);
			
		StringBuffer b = new StringBuffer();
		b.append(read(mainFile));
		
		File[] ff = dir.listFiles(new FileFilter1());
		for(File f:ff)
		{
			b.append("\n\n");
			b.append(read(f));
		}
		return b.toString();
	}
	
	
	
	private String read(File file) throws Exception
	{
		String text = (String) readFile.t(file);
		return text.replace("\r","").trim();
	}
	
	
	
	private class FileFilter1 implements FileFilter
	{
		public boolean accept(File f)
		{
			if(!f.isFile()) return false;
			String name = f.getName();
			return name.endsWith(".java") && !name.equals(MAINNAME);
		}	
	}
}
