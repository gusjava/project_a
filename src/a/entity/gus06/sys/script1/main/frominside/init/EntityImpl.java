package a.entity.gus06.sys.script1.main.frominside.init;

import a.framework.*;
import java.io.File;
import java.io.PrintStream;

public class EntityImpl implements Entity, Runnable {

	public String creationDate() {return "20161012";}

	public static final String NAME = "main";

	private Service main;
	private Service findInput;
	private Service getScript;
	
	private String input;
	private PrintStream out;
	private Thread t;


	public EntityImpl() throws Exception
	{
		main = Outside.service(this,"gus06.sys.script1.main.main1");
		findInput = Outside.service(this,"gus06.sys.script1.main.fromargs.init.find");
		getScript = Outside.service(this,"gus06.app.inside.script2");
		 
		out = (PrintStream) Outside.resource(this,"sysout");
		
		input = findInput();
		if(input==null) return;
		
		t = new Thread(this,"THREAD_"+getClass().getName());
		t.start();
	}
	
	
	private String findInput() throws Exception
	{
		String v = (String) findInput.g();
		if(v!=null) return v;
		return (String) getScript.r(NAME);
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
