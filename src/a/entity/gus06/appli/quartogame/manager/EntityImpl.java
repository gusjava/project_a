package a.entity.gus06.appli.quartogame.manager;

import a.framework.*;
import java.util.Timer;
import java.util.Date;
import java.util.TimerTask;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl extends S1 implements Entity, E, R, G, P, Runnable {

	public String creationDate() {return "20191117";}
	
	public static final long LAPSE = 1000;


	private Service engine;
	private Service findPlayer;
	
	private Object player1;
	private Object player2;
	
	private boolean isP1;
	private Object input;
	private List history;
	
	private Thread t;
	private Timer timer;
	private TimerTask task;
	
	
	
	
	public EntityImpl() throws Exception
	{
		engine = Outside.service(this,"gus06.appli.quartogame.engine");
		findPlayer = Outside.service(this,"gus06.appli.quartogame.player.find");
		
		task = new TimerTask(){public void run(){triggerPlay();}};
		timer = new Timer("TIME_"+getClass().getName());
		timer.schedule(task,new Date(),LAPSE);
		
		history = new ArrayList();
		init();
	}
	
	
	public Object g() throws Exception
	{return engine.g();}
	
	public Object r(String key) throws Exception
	{return engine.r(key);}
	
	
	
	
	public void e() throws Exception
	{
		init();
		dataChanged();
	}
	
	
	private void init() throws Exception
	{
		engine.e();
		history.clear();
		isP1 = true;
		
		player1 = findPlayer.r("P1");
		player2 = findPlayer.r("P2");
		
		if(player1!=null) ((E) player1).e();
		if(player2!=null) ((E) player2).e();
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		if(t!=null && t.isAlive()) return;
		input = obj;
		triggerPlay();
	}
	
	
	
	private synchronized void triggerPlay()
	{
		if(t!=null && t.isAlive()) return;
		t = new Thread(this,"THREAD_"+getClass().getName());
		t.start();
	}
	
	
	
	public void run()
	{
		try
		{
			Object player = getPlayer();
			Object input1 = getInput1(player);
			input = null;
			
			if(input1==null) return;
			if(!engine.f(input1)) return;
		
			isP1 = !isP1;
			history.add(engine.r("data"));
			dataChanged();
		}
		catch(Exception e)
		{Outside.err(this,"run()",e);}
	}
	
	
	
	private Object getPlayer()
	{return isP1?player1:player2;}
	
	
	
	private Object getInput1(Object player) throws Exception
	{
		if(player==null) return input;
		Object board = engine.r("data_");
		return ((T)player).t(board);
	}
	
	
	
	private void dataChanged()
	{send(this,"dataChanged()");}
}
