package a.entity.gus06.appli.gusexplorer.scripts.startup.manager;

import a.framework.*;
import java.io.File;
import java.io.PrintStream;

public class EntityImpl implements Entity, R, Runnable {

	public String creationDate() {return "20160918";}
	
	public static final String KEY_ENABLE = "startup.scripts.enable";


	private Service executor0;
	private Service executor1;
	private Service extractLnk;
	private Service propBoolDT;
	
	private PrintStream out;
	private File dir;
	private Thread t;
	
	
	public EntityImpl() throws Exception
	{
		executor0 = Outside.service(this,"gus06.appli.gusexplorer.scripts.startup.manager.executor0");
		executor1 = Outside.service(this,"gus06.appli.gusexplorer.scripts.startup.manager.executor1");
		extractLnk = Outside.service(this,"gus06.file.lnk.extract.path");
		propBoolDT = Outside.service(this,"propbool_dt");
		
		out = (PrintStream) Outside.resource(this,"sysout");
		
		if(!propBoolDT.f(KEY_ENABLE))
		{
			out.println("Startup disabled");
			return;
		}
		
		File dir0 = (File) Outside.resource(this,"defaultdir");
		dir = new File(dir0,"scripts_startup");
		dir.mkdirs();
		
		t = new Thread(this,"THREAD_"+getClass().getName());
		t.start();
	}
	
	
	public void run()
	{
		File[] files = dir.listFiles();
		for(File file:files) handle(file);
		out.println("Startup complete");
	}
	
	
	private void handle(File file)
	{
		try
		{
			if(!file.isFile()) return;
			
			file = (File) extractLnk.t(file);
			if(!file.getName().endsWith(".gus")) return;
			
			P p = file.getName().startsWith("_") ? executor1 : executor0;
			
			p.p(file);
			out.println("Script launched at startup: "+file.getName());
		}
		catch(Exception e)
		{Outside.err(this,"handle(File)",e);}
	}
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("dir")) return dir;
		if(key.equals("keys")) return new String[]{"dir"};
		
		throw new Exception("Unknown key: "+key);
	}
}
