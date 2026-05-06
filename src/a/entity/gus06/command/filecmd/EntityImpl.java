package a.entity.gus06.command.filecmd;

import a.framework.*;
import java.io.File;
import java.io.PrintStream;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140725";}

	private Service fp;
	private Service readFile;
	private Service execute;

	private PrintStream out;


	public EntityImpl() throws Exception
	{
		fp = Outside.service(this,"fileprovider");
		readFile = Outside.service(this,"gus.x.file.string.read.v1");
		execute = Outside.service(this,"gus06.command.execute");

		out = (PrintStream) Outside.resource(this,"sysout");
	}

	
	
	public void p(Object obj) throws Exception
	{
		File file = toFile(obj);
		out.println("executing script: "+file);

		String s = (String) readFile.t(file);
		String[] n = s.split("[\n\r]+");
		for(int i=0;i<n.length;i++)
		{
			out.println("line "+i+":");
			execute.p(n[i]);
		}
	}


	private File toFile(Object obj) throws Exception
	{
		if(obj instanceof File) return (File) obj;
		if(obj instanceof String) return (File) fp.r((String) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
