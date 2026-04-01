package a.entity.gus06.entitydev.dirtopseudoset;

import a.framework.*;
import java.io.File;
import java.io.FileFilter;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150823";}


	public static final FileFilter DIRFILTER = new FileFilter(){
		public boolean accept(File f) {return f.isDirectory();}
	};
	
	
	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		
		File dir1 = new File(dir,"gus06");
		File dir2 = new File(dir1,"entity");
		
		if(!dir2.isDirectory()) return null;
		
		File[] dd = dir2.listFiles(DIRFILTER);
		
		Set set = new HashSet();
		for(File d:dd) set.add(d.getName());
		return set;
	}
}
