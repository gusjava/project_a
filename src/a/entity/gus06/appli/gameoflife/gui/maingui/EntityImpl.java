package a.entity.gus06.appli.gameoflife.gui.maingui;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;


public class EntityImpl implements Entity, ActionListener, I, V, R, P {

	public String creationDate() {return "20150323";}

	public static final long DEFAULT_SLEEP = 50;
	public static final int DEFAULT_SIZE = 200;
	
	private Service transition;
	private Service screen;
	private Service readState;
	private Service generateRandom;

	private JPanel panel;
	private JButton button;
	
	private Timer timer;
	private TimerTask task;
	private boolean[][] state;
	private long sleep = DEFAULT_SLEEP;
	private int size = DEFAULT_SIZE;
	
	private T initializer;
	
	

	public EntityImpl() throws Exception
	{
		transition = Outside.service(this,"gus06.appli.gameoflife.transition");
		screen = Outside.service(this,"*gus06.appli.gameoflife.gui.screen");
		readState = Outside.service(this,"gus06.appli.gameoflife.state.read");
		generateRandom = Outside.service(this,"gus06.appli.gameoflife.state.random");
		
		timer = new Timer("TIMER_"+getClass().getName());

		button = new JButton("D�marrer");
		button.setFont(button.getFont().deriveFont((float) 15));
		button.addActionListener(this);
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) screen.i(),BorderLayout.CENTER);
		panel.add(button,BorderLayout.SOUTH);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("sleep"))
		{sleep = (Long) obj;return;}
		
		if(key.equals("size"))
		{size = (Integer) obj;return;}
		
		if(key.equals("initializer"))
		{initializer = (T) obj;return;}
		
		if(key.equals("initialData"))
		{initializer = (T) readState.t(obj);return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	public void p(Object obj) throws Exception
	{
		String cmd = (String) obj;
		
		if(cmd.equals("start")) {start();;return;}
		
		throw new Exception("Unknown command: "+cmd);
	}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("sleep")) return sleep;
		if(key.equals("size")) return size;
		if(key.equals("screen")) return screen;
		
		if(key.equals("keys")) return new String[]{"sleep","size","screen"};
		
		throw new Exception("Unknown key: "+key);	
	}


	public void actionPerformed(ActionEvent e)
	{start();}
	
	
	
	
	private synchronized void start()
	{
		try
		{
			if(task!=null) task.cancel();
			task = new TimerTask() {public void run() {next();}};
			
			state = initialState();
			timer.schedule(task,new Date(),sleep);
		}
		catch(Exception e)
		{Outside.err(this,"start()", e);}
	}
	
	
	
	
	private synchronized void next()
	{
		try
		{
			screen.p(state);
			state = (boolean[][]) transition.t(state);
		}
		catch(Exception e)
		{Outside.err(this,"next()",e);}
	}
	
	
	private boolean[][] initialState() throws Exception
	{
		if(initializer!=null) return (boolean[][]) initializer.t(size);
		return (boolean[][]) generateRandom.t(size);
	}
}