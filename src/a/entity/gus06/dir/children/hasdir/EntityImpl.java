package a.entity.gus06.dir.children.hasdir;

import java.io.File;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160609";}
	
	
	public boolean f(Object obj) throws Exception
	{
		File dir = (File) obj;
		if(!dir.exists()) return false;
		
		if(!dir.isDirectory()) throw new Exception("Dir expected: "+dir);
		
		File[] ff = dir.listFiles();
		if(ff==null) return false;
		for(File f:ff) if(f.isDirectory()) return true;
		return false;
	}
}
