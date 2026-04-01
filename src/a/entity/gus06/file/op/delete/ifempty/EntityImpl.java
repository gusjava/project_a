package a.entity.gus06.file.op.delete.ifempty;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220612";}


	public void p(Object obj) throws Exception
	{
		File file = (File) obj;
		if(!file.exists()) return;
		if(file.length()>0) return;
		
		boolean r = file.delete();
		if(!r) throw new Exception("Failed to delete file: "+file);
	}
}