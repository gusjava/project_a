package a.entity.gus06.dir.children.dirtoset.name0;

import a.framework.*;
import java.io.File;
import java.io.FileFilter;
import java.util.Set;
import java.util.HashSet;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141123";}

	public static final FileFilter FILEFILTER = new FileFilter() {
		public boolean accept(File f) {return f.isFile();}
	};
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		File dir = (File) obj;
		File[] ff = dir.listFiles(FILEFILTER);
		if(ff==null) return null;
		
		Set set = new HashSet();
		for(File f:ff) set.add(name0(f));
		return set;
	}
	
	
	
	private String name0(File f)
	{
		String name = f.getName();
		if(!name.contains(".")) return name;
		
		String[] n = name.split("\\.");
		String ext = n[n.length-1];
		
		return name.substring(0,name.length()-ext.length()-1);
	}
}
