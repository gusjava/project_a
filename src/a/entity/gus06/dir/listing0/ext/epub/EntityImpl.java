package a.entity.gus06.dir.listing0.ext.epub;

import java.io.File;
import java.io.FileFilter;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191010";}

	
	public static final String FILEEXTENSION = "epub";
	
	public static final FileFilter FILEFILTER = new FileFilter(){
		public boolean accept(File f)
		{return f.isFile() && f.getName().toLowerCase().endsWith("."+FILEEXTENSION);}
	};


	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		return dir.listFiles(FILEFILTER);
	}
}
