package a.entity.gus.x.dir.listing0.dirs;

import java.io.File;
import java.io.FileFilter;

import a.framework.Entity;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240120";}
	
	public static final FileFilter DIRFILTER = new FileFilter(){
		public boolean accept(File f) {return f.isDirectory();}
	};

	public Object t(Object obj) throws Exception {
		File dir = (File) obj;
		if (dir==null || !dir.isDirectory())
			return null;
		return dir.listFiles(DIRFILTER);
	}
}
