package a.entity.gus06.dirfile.op.initfile;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151114";}

	
	
	public void p(Object obj) throws Exception
	{
		File f = (File) obj;
		
		if(f.isDirectory()) throw new Exception("Path is already an existing directory: "+f);
		if(f.isFile()) return;
		
		f.getParentFile().mkdirs();
		f.createNewFile();
	}
}
