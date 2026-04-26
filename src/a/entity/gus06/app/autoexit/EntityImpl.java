package a.entity.gus06.app.autoexit;

import java.io.File;
import java.io.PrintStream;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import a.framework.*;

public class EntityImpl extends TimerTask implements Entity {
	public String creationDate() {return "20140704";}

	public static final long LAPSE = 500;
	
	private Service perform;
	private PrintStream out;
	private File file;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"m097.e.exit.ask");
		out = (PrintStream) Outside.resource(this,"sysout");
		file = new File("stop");
		new Timer("TIMER_"+getClass().getName()).schedule(this,new Date(),LAPSE);
	}

	public void run()
	{
		if(!file.isFile()) return;
		file.delete();
		
		out.println("stop file detected: exiting application");
		
		try{perform.e();}
		catch(Exception e)
		{Outside.err(this,"run()",e);}
		
		System.exit(1);
	}
}
