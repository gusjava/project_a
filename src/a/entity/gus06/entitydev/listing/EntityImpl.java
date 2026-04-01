package a.entity.gus06.entitydev.listing;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140703";}
	
	
	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		ArrayList list = new ArrayList();
		
		dir = new File(new File(dir,"gus06"),"entity");
		if(!dir.isDirectory()) return list;
		
		int rootLength = dir.getAbsolutePath().length();
		scan(dir,rootLength,list);
		
		Collections.sort(list);
		return list;
	}
	
	
	
	private void scan(File path, int rootLength, ArrayList list)
	{
		if(isEntityFile(path))
			list.add(toEntityName(path,rootLength));
		
		if(path.isDirectory())
		{
			File[] ff = path.listFiles();
			for(File f:ff) scan(f,rootLength,list);
		}
	}
	
	
	
	private boolean isEntityFile(File f)
	{return f.isFile() && f.getName().equals("EntityImpl.java");}
	
	
	
	private String toEntityName(File f, int rootLength)
	{
		String p = f.getParent();
		return p.substring(rootLength+1).replace(File.separator,".");
	}
}
