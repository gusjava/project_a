package a.entity.gus06.dir.listing0.files;

import java.io.File;
import java.io.FileFilter;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150620";}

	
	public static final FileFilter FILEFILTER = new FileFilter(){
		public boolean accept(File f) {return f.isFile();}
	};


	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		if(dir==null || !dir.isDirectory()) return null;
		return dir.listFiles(FILEFILTER);
	}
}