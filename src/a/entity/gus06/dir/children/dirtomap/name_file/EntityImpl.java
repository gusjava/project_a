package a.entity.gus06.dir.children.dirtomap.name_file;

import a.framework.*;
import java.io.File;
import java.io.FileFilter;
import java.util.Map;
import java.util.HashMap;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140902";}

	public static final FileFilter FILEFILTER = new FileFilter() {
		public boolean accept(File f) {return f.isFile();}
	};
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		File dir = (File) obj;
		File[] ff = dir.listFiles(FILEFILTER);
		if(ff==null) return null;
		
		Map map = new HashMap();
		for(File f:ff) map.put(f.getName(),f);
		return map;
	}
}
