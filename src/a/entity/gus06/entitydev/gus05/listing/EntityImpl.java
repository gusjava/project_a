package a.entity.gus06.entitydev.gus05.listing;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import a.framework.*;
import java.util.HashSet;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150525";}
	
	
	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		
		dir = new File(new File(dir,"gus05"),"entity");
		if(!dir.isDirectory()) return new ArrayList();
		
		Set set = new HashSet();
		int rootLength = dir.getAbsolutePath().length();
		scan(dir,rootLength,set);
		
		ArrayList list = new ArrayList(set);
		Collections.sort(list);
		return list;
	}
	
	
	
	private void scan(File path, int rootLength, Set set)
	{
		if(isJavaFile(path))
			set.add(toEntityName(path,rootLength));
		
		if(path.isDirectory())
		{
			File[] ff = path.listFiles();
			for(File f:ff) scan(f,rootLength,set);
		}
	}
	
	
	
	private boolean isJavaFile(File f)
	{return f.isFile() && f.getName().endsWith(".java");}
	
	
	
	private String toEntityName(File f, int rootLength)
	{
		String p = f.getParent();
		return p.substring(rootLength+1).replace(File.separator,".");
	}
}
