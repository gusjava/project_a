package a.entity.gus06.file.isfile.casesensitive;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20201219";}
	
	
	public boolean f(Object obj) throws Exception
	{
		return isRealFile((File) obj);
	}
	
	private boolean isRealFile(File file)
	{
		if(!file.isFile()) return false;
		
		String name = file.getName();
		String[] nn = file.getParentFile().list();
		for(String n : nn) if(n.equals(name)) return true;
		return false;
	}
}
