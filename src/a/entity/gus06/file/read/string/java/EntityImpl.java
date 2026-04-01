package a.entity.gus06.file.read.string.java;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140730";}

	private Service readFile;

	public EntityImpl() throws Exception
	{readFile = Outside.service(this,"gus06.file.read.string");}
	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		if(!file.getName().endsWith(".java")) throw new Exception("Java file has invalid name: "+file.getName());
		if(!file.isFile()) throw new Exception("Java file not found: "+file);
		if(file.length()==0)  throw new Exception("Java file is empty: "+file);
		
		return readFile.t(file);
	}
}
