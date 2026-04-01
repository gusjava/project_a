package a.entity.gus06.x.dir.listing0.files.java;

import java.io.File;
import java.io.FileFilter;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251111";}
	
	public static final String EXT = "java";

	public Object t(Object obj) throws Exception {
		File dir = (File) obj;
		if (dir==null || !dir.isDirectory()) return null;

		return dir.listFiles(new FileFilter()
		{
			public boolean accept(File f)
			{return f.isFile() && f.getName().endsWith("." + EXT);}
		});
	}
}
