package a.entity.gus06.java.compiler.v3.handleprocess;

import a.framework.*;
import java.io.PrintStream;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20200304";}

	public static final long TIMEOUT = 180000L;

	private Service killer;
	private Service ioTransfer;
	private Service readArray;


	public EntityImpl() throws Exception
	{
		killer = Outside.service(this,"gus06.process.timeout.killer");
		ioTransfer = Outside.service(this,"gus.x.io.transfer.toprintstream");
		readArray = Outside.service(this,"gus06.file.read.string.array");
	}


	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		Process process = (Process) o[0];
		PrintStream out = (PrintStream) o[1];
		String[] cmd = (String[]) o[2];
		File listingFile = (File) o[3];
		
		
		out.println("> "+cmd[0]);
		
		String[] listing = (String[]) readArray.t(listingFile);
		for(String line : listing)
		out.println("* "+line);
		
		long t1 = System.currentTimeMillis();
		int code = handleProcess(process,out);
		long dt = System.currentTimeMillis()-t1;
		
		out.println("- duration: "+dt+" ms");
		out.println("- "+resultMessage(code));
		
		if(code!=0) throw new Exception("compilation failed (code="+code+")");
	}



	private int handleProcess(Process process, PrintStream out) throws Exception
	{
		killer.p(new Object[]{process,Long.valueOf(TIMEOUT)});
		ioTransfer.p(new Object[]{process.getInputStream(),out});
		return process.exitValue();
	}
	
	private String resultMessage(int code)
	{
		if(code==0) return "compilation successful";
		return "compilation failed (code="+code+")";
	}
}