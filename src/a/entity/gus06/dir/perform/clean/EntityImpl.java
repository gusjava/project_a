package a.entity.gus06.dir.perform.clean;

import java.io.File;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150630";}
	
	
	public void p(Object obj) throws Exception
	{
		File dir = (File) obj;
		if(!dir.isDirectory())
			throw new Exception("Invalid directory: "+dir);

		cleanDir(dir);
	}
	
	private void cleanDir(File dir) throws Exception
	{
		File[] ff = dir.listFiles();
		for(File f:ff) if(f.isDirectory()) cleanDir(f);
		if(dir.list().length==0) deleteDir(dir);
	}
	
	private void deleteDir(File dir) throws Exception
	{
		boolean r = dir.delete();
		if(!r) throw new Exception("Dir cound not be deleted: "+dir);
	}
}