package a.entity.gus06.file.neighbours.findfiles;

import a.framework.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.io.FileFilter;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150924";}

	public static final FileFilter FILTER = new FileFilter() {
		public boolean accept(File f) {return f.isFile();}
	};
	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		File parent = file.getParentFile();
		
		List l = new ArrayList();
		File[] ff = parent.listFiles(FILTER);
		for(File f:ff) if(!f.equals(file)) l.add(f);
		
		return l;
	}
}
