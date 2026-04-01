package a.entity.gus06.data.perform.exec.asbatch.prepare;

import a.framework.*;
import java.io.File;
import java.io.PrintStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20230215";}

	public static final String DEFAULT_EXT = "bat";

	private Service tmpFile;

	public EntityImpl() throws Exception
	{
		tmpFile = Outside.service(this,"gus06.file.tmpfile");
	}
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof String) return fromString((String) obj);
		if(obj instanceof String[]) return fromArray((String[]) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private File fromString(String s) throws Exception
	{
		return buildFile(s, DEFAULT_EXT);
	}
	
	private File fromArray(String[] array) throws Exception
	{
		if(array.length!=2) throw new Exception("Wrong array number: "+array.length);
		return buildFile(array[0], array[1]);
	}
	
	private File buildFile(String cmd, String ext) throws Exception
	{
		File file = (File) tmpFile.t(ext);
		PrintStream p = new PrintStream(file);
		p.print(cmd);
		p.close();
		return file;
	}
}