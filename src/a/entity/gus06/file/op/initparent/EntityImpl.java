package a.entity.gus06.file.op.initparent;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190105";}

	
	public void p(Object obj) throws Exception
	{
		File file = (File) obj;
		
		File parent = file.getParentFile();
		if(!parent.exists()) parent.mkdirs();
	}
}
