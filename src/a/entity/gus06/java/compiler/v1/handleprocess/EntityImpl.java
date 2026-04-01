package a.entity.gus06.java.compiler.v1.handleprocess;

import a.framework.*;
import java.io.PrintStream;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140725";}

	public static final long TIMEOUT = 120000L;

	private Service killer;
	private Service ioTransfer;
	private PrintStream out;


	public EntityImpl() throws Exception
	{
		killer = Outside.service(this,"gus06.process.timeout.killer");
		ioTransfer = Outside.service(this,"gus06.io.transfer.toprintstream");
		out = (PrintStream) Outside.resource(this,"g#gus06.java.compiler.outputanalyzer");
	}


	public void p(Object obj) throws Exception
	{
		int code = pr((Process) obj);
		if(code==0) out.println("compilation successful");
		else
		{
			out.println("compilation failed (code="+code+")");
			throw new Exception("compilation failed (code="+code+")");
		}
	}


	public boolean f(Object obj) throws Exception
	{return pr((Process) obj)==0;}


	private int pr(Process pr) throws Exception
	{
		killer.p(new Object[]{pr,Long.valueOf(TIMEOUT)});
		ioTransfer.p(new Object[]{pr.getErrorStream(),out});
		return pr.exitValue();
	}
}