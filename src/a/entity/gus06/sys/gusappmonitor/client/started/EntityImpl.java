package a.entity.gus06.sys.gusappmonitor.client.started;

import a.framework.*;
import java.io.PrintStream;
import java.util.Map;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Timer;
import java.util.Date;
import java.util.TimerTask;

public class EntityImpl implements Entity, ActionListener, P {

	public String creationDate() {return "20190316";}
	
	public static final String INIT = "INIT";
	
	public static final long LAPSE = 100;

	
	private Service infoMap;
	private Service exitSup;
	private Service encodeMap;
	private Service buildStringMap;
	private Service perform;
	
	private PrintStream out;
	private Timer timer;
	private TimerTask task;
	private Map map;
	private P p;
	
	

	public EntityImpl() throws Exception
	{
		infoMap = Outside.service(this,"gus06.app.infomap");
		exitSup = Outside.service(this,"g#gus.y.cust1.rb.guiexit");
		encodeMap = Outside.service(this,"gus.x.tostring.map.urlencoding");
		buildStringMap = Outside.service(this,"gus06.map.build.stringmap");
		perform = Outside.service(this,"gus06.sys.gusappmonitor.client.perform");
		
		out = (PrintStream) Outside.resource(this,"sysout");
		timer = new Timer("TIMER_"+getClass().getName());
		task = new TimerTask(){public void run() {perform();}};
		map = (Map) infoMap.g();
	}
	
	
	public void p(Object obj) throws Exception
	{
		p = (P) obj;
		
		out.println("Connected to GusAppMonitor");
		exitSup.addActionListener(this);
		
		Map strMap = (Map) buildStringMap.t(map);
		String init = (String) encodeMap.t(strMap);
		
		p.p(INIT+":"+init);
		
		timer.schedule(task,new Date(),LAPSE);
	}


	public void actionPerformed(ActionEvent e)
	{beforeExit();}
	
	
	private void beforeExit()
	{
		send("#END#");
	}
	
	
	private void perform()
	{
		try
		{perform.p(p);}
		catch(Exception e)
		{Outside.err(this,"perform()",e);}
	}



	private void send(String message)
	{
		if(p==null) return;
		try{p.p(message);}
		catch(Exception e)
		{Outside.err(this,"send(String)",e);}
	}
}
