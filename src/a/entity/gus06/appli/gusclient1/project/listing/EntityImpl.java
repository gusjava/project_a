package a.entity.gus06.appli.gusclient1.project.listing;

import a.framework.*;
import java.io.File;
import java.io.FileFilter;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20140830";}

	public static final FileFilter DIRFILTER = new FileFilter() {
		public boolean accept(File f) {return f.isDirectory();}
	};

	private File rootDir;
	
	public EntityImpl() throws Exception
	{
		rootDir = (File) Outside.resource(this,"path#path.projectdir");
		rootDir.mkdirs();
	}
	
	
	public Object g() throws Exception
	{
		File[] dirs = rootDir.listFiles(DIRFILTER);
		String[] names = new String[dirs.length];
		for(int i=0;i<dirs.length;i++) names[i] = dirs[i].getName();
		return names;
	}
}
