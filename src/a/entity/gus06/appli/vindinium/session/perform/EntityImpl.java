package a.entity.gus06.appli.vindinium.session.perform;

import java.io.PrintStream;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import a.framework.*;

public class EntityImpl extends S1 implements Entity, P, G, R {

	public String creationDate() {return "20170917";}

	public static final long DELAY = 10*60*1000;
	
	
	private Service sessionParams;
	private Service retrieveData;
	private Service mainBot;

	private PrintStream out;
	private Map data;
	private String direction;
	private Exception exception;
	
	private String mode;
	private int times_current;
	private int times_total;
	private boolean is_restart;
	
	private Timer timer;
	private TimerTask task;
	
	private boolean performing = false;
	
	

	public EntityImpl() throws Exception
	{
		sessionParams = Outside.service(this,"gus06.appli.vindinium.session.params");
		retrieveData = Outside.service(this,"gus06.appli.vindinium.data.retrievedata");
		mainBot = Outside.service(this,"gus06.appli.vindinium.bot.mainbot");
		
		out = (PrintStream) Outside.resource(this,"sysout");
		
		timer = new Timer("TIMER_"+getClass().getName());
	}
	
	
	public Object g() throws Exception
	{return data;}


	public void p(Object obj) throws Exception
	{
		initSession((String) obj);
		performSession();
	}
	

	public Object r(String key) throws Exception
	{
		if(key.equals("exception")) return exception;
		if(key.equals("keys")) return new String[]{"exception"};
		throw new Exception("Unknown key: "+key);
	}
	
	
	private String param(String key) throws Exception
	{return (String) sessionParams.r(key);}
	
	
	private void initSession(String mode) throws Exception
	{
		if(performing) return;
		performing = true;
		
		if(task!=null) task.cancel();
		task = null;
		
		this.mode = mode;
		times_total = getTimes();
		is_restart = isRestart();
	}

	
	private void performSession()
	{
		performing = true;
		sessionStarted();
		
		out.println("Initializing game session with mode: "+mode+" & times="+times_total);
		
		for(int i=0;i<times_total;i++)
		{
			times_current = i;
			performGame();
		}
		
		out.println("Game session is over");
		
		if(is_restart)
		{
			out.println("Auto restarting after "+DELAY+" ms");
			task = new TimerTask() {public void run()
				{if(!performing) performSession();}
			};
			timer.schedule(task,DELAY);
		}

		sessionEnded();
		performing = false;
	}

	

	private void performGame()
	{
		try
		{
			gameStarted();
			
			exception = null;
			out.println("Starting game "+times_current+"/"+times_total);
			mainBot.e();

			initData();
			while(data!=null) updateData();
			
			gameEnded();
		}
		catch(Exception e)
		{
			exception = e;
			gameFailed();
			Outside.err(this,"performGame()",e);
		}
	}
	
	
	
	private void initData() throws Exception
	{
		retrieveData.v(PARAMS.MODE,mode);
		retrieveData.v(PARAMS.TURNS,param(PARAMS.TURNS));
		retrieveData.v(PARAMS.KEY,param(PARAMS.KEY));
		retrieveData.v(PARAMS.MAP,param(PARAMS.MAP));
		
		data = (Map) retrieveData.g();
	}
	
	
	
	
	private void updateData() throws Exception
	{
		data.put(DATA_._MODE,mode);
		data.put(DATA_._TIMES,new int[]{times_current,times_total});
		data.put(DATA_._KEY,param(PARAMS.KEY));
		
		updateData_1();
		updateData_2();
	}
	
	
	private void updateData_1() throws Exception
	{
		try
		{
			direction = computeNextDirection();
			dataComputed();
		}
		catch(Exception e)
		{throw new Exception("Failed to compute next direction",e);}
	}
	
	
	
	private void updateData_2() throws Exception
	{
		try
		{
			data = findNextData();
			dataRetrieved();
		}
		catch(Exception e)
		{throw new Exception("Failed to retrieve next data",e);}
	}
	
	
	
	private String computeNextDirection() throws Exception
	{
		if(data==null) return null;
		return (String) mainBot.t(data);
	}
	
	
	
	private Map findNextData() throws Exception
	{
		if(direction==null) return null;
		return (Map) retrieveData.t(direction);
	}
	
	
	private int getTimes() throws Exception
	{
		String s = param(PARAMS.TIMES);
		if(s==null || s.equals("")) return 1;
		return Integer.parseInt(s);
	}
	
	
	private boolean isRestart() throws Exception
	{
		String s = param(PARAMS.RESTART);
		if(s==null || s.equals("")) return false;
		return Boolean.parseBoolean(s);
	}
	
	
	
	private void dataComputed()
	{send(this,"dataComputed()");}
	
	private void dataRetrieved()
	{send(this,"dataRetrieved()");}
	
	private void gameStarted()
	{send(this,"gameStarted()");}
	
	private void gameEnded()
	{send(this,"gameEnded()");}
	
	private void gameFailed()
	{send(this,"gameFailed()");}
	
	private void sessionStarted()
	{send(this,"sessionStarted()");}
	
	private void sessionEnded()
	{send(this,"sessionEnded()");}
}
