package a.entity.gus06.file.op.delete;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150606";}


	public void p(Object obj) throws Exception
	{
		File file = (File) obj;
		if(!file.exists()) return;
		
		boolean r = file.delete();
		if(!r) throw new Exception("Failed to delete file: "+file);
	}
}
