package a.entity.gus06.sys.filemapper1.idtofile.zip;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231127";}
	
	public Object t(Object obj) throws Exception
	{
		throw new Exception("Invalid idToFile call for zip filemapper");
	}
}