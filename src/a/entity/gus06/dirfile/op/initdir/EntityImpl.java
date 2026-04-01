package a.entity.gus06.dirfile.op.initdir;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151114";}

	
	
	public void p(Object obj) throws Exception
	{
		File f = (File) obj;
		
		if(f.isFile()) throw new Exception("Path is already an existing file: "+f);
		if(f.isDirectory()) return;
		
		f.mkdirs();
	}
}
