package a.entity.gus06.dirfile.contains.filetof;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231019";}


	private Service handleDir;
	private Service handleFile;

	public EntityImpl() throws Exception
	{
		handleDir = Outside.service(this,"gus06.dir.contains.filetof");
		handleFile = Outside.service(this,"gus06.file.contains.filetof");
	}
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		if(!file.exists()) throw new Exception("File not found: "+file);
		if(file.isDirectory()) return handleDir.t(obj);
		return handleFile.t(obj);
	}
}