package a.entity.gus06.process.holder1;

import a.framework.*;
import java.io.PrintStream;

public class EntityImpl implements Entity, T, P, V {

	public String creationDate() {return "20141231";}


	private Service transfer;

	private PrintStream p_out;
	private PrintStream p_err;
	private PrintStream p_exit;
	
	
	
	public EntityImpl() throws Exception
	{
		transfer = Outside.service(this,"gus06.io.transfer.th.toprintstream");
		
		p_out = (PrintStream) Outside.resource(this,"sysout");
		p_err = (PrintStream) Outside.resource(this,"sysout");
		p_exit = (PrintStream) Outside.resource(this,"sysout");
	}
	
	
	public void p(Object obj) throws Exception
	{t(obj);}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Process proc = (Process) obj;
		
		transfer.p(new Object[]{proc.getInputStream(),p_out});
		transfer.p(new Object[]{proc.getErrorStream(),p_err});
		return new ExitValueCollector(proc,p_exit);
	}

	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("p_out")) {p_out = (PrintStream) obj;return;}
		if(key.equals("p_err")) {p_err = (PrintStream) obj;return;}
		if(key.equals("p_exit")) {p_exit = (PrintStream) obj;return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	
	
	
	private class ExitValueCollector extends S1 implements Runnable
	{
		private Process proc;
		private PrintStream p;
		
		public ExitValueCollector(Process proc, PrintStream p)
		{
			this.proc = proc;
			this.p = p;
			startProcess(this);
		}
	
		public void run()
		{
			try
			{
				int exitValue = proc.waitFor();
				p.println("exit value = "+exitValue+"\n");
			}
			catch(InterruptedException e)
			{p_out.println("ERR: "+e);}
			processOver();
		}
	
		private void processOver()
		{send(this,"processOver()");}
	}
	
	
	private void startProcess(Runnable r)
	{new Thread(r,"THREAD_"+getClass().getName()).start();}
}
