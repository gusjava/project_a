package a.entity.gus06.dir.perform.removefiles0;

import java.io.File;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140725";}
	
	
	public void p(Object obj) throws Exception
	{
		File dir = (File) obj;
		if(!dir.isDirectory())
			throw new Exception("Invalid directory: "+dir);

		File[] ff = dir.listFiles();
		for(File f:ff) if(f.isFile()) delete(f);
	}


	private void delete(File f) throws Exception
	{
		boolean r = f.delete();
		if(!r) throw new Exception("Failed to delete "+f);
	}
}
