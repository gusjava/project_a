package a.entity.gus06.sys.filemanagement1.tool.allocine.md5.find.code;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201006";}
	
	
	private Service readFile;
	private Service findFile;

	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus06.file.read.string");
		findFile = Outside.service(this,"gus06.sys.filemanagement1.tool.allocine.md5.find.file");
	}
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) findFile.t(obj);
		if(file==null || !file.exists()) return null;
		return readFile.t(file);
	}
}
