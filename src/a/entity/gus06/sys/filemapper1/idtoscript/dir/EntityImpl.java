package a.entity.gus06.sys.filemapper1.idtoscript.dir;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231127";}

	private Service idToFile;
	private Service readFile;

	public EntityImpl() throws Exception
	{
		idToFile = Outside.service(this,"gus06.sys.filemapper1.idtofile.dir");
		readFile = Outside.service(this,"gus.x.file.string.read.v1");
	}
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) idToFile.t(obj);
		return readFile.t(file);
	}
}