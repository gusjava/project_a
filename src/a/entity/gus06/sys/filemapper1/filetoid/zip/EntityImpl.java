package a.entity.gus06.sys.filemapper1.filetoid.zip;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231127";}
	
	public Object t(Object obj) throws Exception
	{
		throw new Exception("Invalid fileToId call for zip filemapper");
	}
}