package a.entity.gus06.x.framework.src.find.packagedir;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251111";}
	
	public Object t(Object obj) throws Exception
	{
		File rootDir = (File) obj;
		return new File(new File(rootDir, "gus06"), "framework");
	}
}