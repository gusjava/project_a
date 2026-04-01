package a.entity.gus06.sys.unrar1.extractall;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20251108";}

	private Service exe;
	
	public EntityImpl() throws Exception
	{
		exe = Outside.service(this,"gus06.sys.unrar1.exe");
	}
	
	public void p(Object obj) throws Exception
	{
		File[] o = (File[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File inputFile = o[0];
		File outputDir = o[1];
		File exeFile = (File) exe.g();
		
		String pathExe = p(exeFile);
		String pathInput = p(inputFile);
		String pathOutput = p(outputDir);
		
		ProcessBuilder pb = new ProcessBuilder(
			pathExe,
			"x",
			pathInput,
			pathOutput
		);
		
		pb.redirectOutput(ProcessBuilder.Redirect.DISCARD);
		pb.redirectError(ProcessBuilder.Redirect.DISCARD);
		Process p = pb.start();
		int code = p.waitFor();
		if(code!=0) throw new Exception("Failed to execute command line");
	}
	
	private String p(File f)
	{return f.getAbsolutePath();}
}