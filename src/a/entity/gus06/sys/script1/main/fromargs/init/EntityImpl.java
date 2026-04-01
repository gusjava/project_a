package a.entity.gus06.sys.script1.main.fromargs.init;

import a.framework.*;
import java.io.File;
import java.io.PrintStream;

public class EntityImpl implements Entity, Runnable {

	public String creationDate() {return "20160602";}


	private Service main;
	private Service findInput;
	
	private String input;
	private PrintStream out;
	private Thread t;
	

	public EntityImpl() throws Exception
	{
		main = Outside.service(this,"gus06.sys.script1.main.main1");
		findInput = Outside.service(this,"gus06.sys.script1.main.fromargs.init.find");
		out = (PrintStream) Outside.resource(this,"sysout");
		
		input = (String) findInput.g();
		if(input==null) return;
		
		t = new Thread(this,"THREAD_"+getClass().getName());
		t.start();
	}
	
	
	
	
	public void run()
	{
		perform();
		System.exit(0);
	}
	
	
	
	private void perform()
	{
		try
		{
			main.p(new Object[]{input,out});
		}
		catch(Exception e)
		{e.printStackTrace();}
	}

}
