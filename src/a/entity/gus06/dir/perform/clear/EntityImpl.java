package a.entity.gus06.dir.perform.clear;

import java.io.File;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140906";}
	
	
	public void p(Object obj) throws Exception
	{
		File dir = (File) obj;
		if(!dir.isDirectory())
			throw new Exception("Invalid directory: "+dir);

		clear(dir);
	}

	
	private void clear(File dir) throws Exception
	{
		while(dir!=null && dir.list().length==0)
		{
			delete(dir);
			dir = dir.getParentFile();
		}
	}
	
	
	private void delete(File dir) throws Exception
	{
		boolean r = dir.delete();
		if(!r) throw new Exception("Dir cound not be deleted: "+dir);
	}

}
