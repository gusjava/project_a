package a.entity.gus06.file.perform.remove;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P, F {

	public String creationDate() {return "20140930";}


	
	public void p(Object obj) throws Exception
	{f(obj);}
	
	
	
	public boolean f(Object obj) throws Exception
	{
		File file = (File) obj;
		delete(file);
		return true;
	}
	
	
	private void delete(File file) throws Exception
	{
		boolean r = file.delete();
		if(!r) throw new Exception("Failed to delete file: "+file);
	}
}
