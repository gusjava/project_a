package a.entity.gus06.app.uniqueid;

import a.framework.*;
import java.io.File;
import java.io.PrintStream;


public class EntityImpl implements Entity, G {

	public String creationDate() {return "20170409";}

	public static final String FILENAME = "unique";

	private Service randomId;
	private Service readFile;
	private File rootDir;
	private File file;
	private String id;
	

	public EntityImpl() throws Exception
	{
		randomId = Outside.service(this,"gus06.data.generate.string.random.alphanum8");
		readFile = Outside.service(this,"gus.x.file.string.read.v1");
		rootDir = (File) Outside.resource(this,"rootdir");
		
		if(!rootDir.exists()) rootDir.mkdirs();
		
		file = new File(rootDir,FILENAME);
		if(file.isFile() && file.length()>0)
		{
			id = readFromFile();
		}
		else
		{
			id = initFile();
		}
	}


	public Object g() throws Exception
	{return id;}
	
	
	
	private String readFromFile() throws Exception
	{
		return (String) readFile.t(file);
	}
	
	private String initFile() throws Exception
	{
		String id = (String) randomId.g();
		PrintStream p = new PrintStream(file);
		p.print(id);
		p.close();
		return id;
	}
}
