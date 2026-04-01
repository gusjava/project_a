package a.entity.gus06.file.string.perform.execute.script1;

import a.framework.*;
import java.io.File;
import java.io.PrintStream;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151024";}

	private Service engine;
	private Service contextBuilder;
	private Service lazyPrintStream;
	private Service writeEx;

	public EntityImpl() throws Exception
	{
		engine = Outside.service(this,"gus06.sys.script1.engine");
		contextBuilder = Outside.service(this,"gus06.sys.script1.context.builder1");
		lazyPrintStream = Outside.service(this,"gus06.io.printstream.lazy.utf8");
		writeEx = Outside.service(this,"gus06.file.write.string.exception");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		if(obj instanceof File)
		{
			File scriptFile = (File) obj;
			File outputFile = new File(scriptFile.getAbsolutePath()+"_output.txt");
			
			execute(scriptFile,outputFile);
		}
		else if(obj instanceof File[])
		{
			File[] f = (File[]) obj;
			if(f.length!=2) throw new Exception("Wrong data number: "+f.length);
			
			File scriptFile = f[0];
			File outputFile = f[1];
			
			execute(scriptFile,outputFile);
		}
		else throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private void execute(File scriptFile, File outputFile) throws Exception
	{
		try
		{
			PrintStream p = (PrintStream) lazyPrintStream.t(outputFile);
			Map context = (Map) contextBuilder.t(p);
			
			engine.p(new Object[]{scriptFile,context});
		}
		catch(Exception e)
		{
			Outside.err(this,"execute(File,File)",e);
			writeEx.p(new Object[]{outputFile,e});
		}
	}
}
