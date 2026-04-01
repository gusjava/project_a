package a.entity.gus06.dir.op.delete.ifempty;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220612";}


	
	
	public void p(Object obj) throws Exception
	{
		File dir = (File) obj;
		if(!dir.exists()) return;
		
		if(!dir.isDirectory())
			throw new Exception("Invalid directory: "+dir);
		
		if(isEmpty(dir)) delete(dir);
	}
	
	
	
	
	private void delete(File dir) throws Exception
	{
		boolean r = dir.delete();
		if(!r) throw new Exception("Failed to delete directory: "+dir);
	}
	
	private boolean isEmpty(File dir)
	{
		File[] ff = dir.listFiles();
		return ff==null || ff.length==0;
	}
}