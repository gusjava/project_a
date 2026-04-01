package a.entity.gus06.file.editor.ext.wav;

import a.framework.*;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import java.io.File;
import javax.swing.Icon;

public class EntityImpl extends S1 implements Entity, ActionListener, I, P, G, R, Runnable {

	public String creationDate() {return "20250520";}
	

	private Service play;
	private Service formPanel;
	
	private Icon iconPlay;
	private Icon iconStop;
	
	private JPanel panel;
	private JButton button;
	private File file;
	private P holder;
	private Thread t;

	public EntityImpl() throws Exception
	{
		play = Outside.service(this,"gus06.file.wav.play");
		formPanel = Outside.service(this,"*gus06.file.wav.gui.metadata.form1");
		
		iconPlay = (Icon) Outside.resource(this,"icon#PLAY2_play");
		iconStop = (Icon) Outside.resource(this,"icon#PLAY2_stop");
		
		button = new JButton("Play");
		button.setIcon(iconPlay);
		button.addActionListener(this);
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) formPanel.i(), BorderLayout.CENTER);
		panel.add(button, BorderLayout.SOUTH);
		
		button.addHierarchyListener(e -> {
			if(!button.isShowing()) reset();
		});
	}
	
	public Object i() throws Exception
	{return panel;}
	
	
	public Object g() throws Exception
	{return file;}
	
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null)
		{
			init(null);
			return;
		}
		if(obj instanceof File)
		{
			init((File) obj);
			return;
		}
		if(obj instanceof String)
		{
			String s = (String) obj;
			if(s.equals("start")) {start();return;}
			if(s.equals("stop")) {reset();return;}
			if(s.equals("shift")) {shiftPlay();return;}
			
			throw new Exception("Unknown command: "+s);
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("file")) return file;
		if(key.equals("comp")) return panel;
		if(key.equals("state")) return state();
		
		if(key.equals("keys")) return new String[]{"file", "comp", "state"};
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	private String state()
	{
		if(isPlaying()) return "playing";
		if(isInitialized()) return "initialized";
		return "empty";
	}
	
	

	public void actionPerformed(ActionEvent e)
	{shiftPlay();}
	
	
	
	private void shiftPlay()
	{
		if(isPlaying()) reset();
		else start();
	}
	
	
	private void init(File file) throws Exception
	{
		if(isPlaying()) reset();
		this.file = file;
		
		if(file!=null && file.isFile() && file.length()>0)
		{
			holder = (P) play.t(file);
			button.setEnabled(true);
		}
		else
		{
			holder = null;
			button.setEnabled(false);
		}
	}
	
	private void reset()
	{
		if(!isInitialized()) return;
		try{close();}
		catch(Exception e)
		{Outside.err(this,"reset()",e);}
		
		button.setText("Play");
		button.setIcon(iconPlay);
		t = null;
		stopped();
	}
	
	private void start()
	{
		if(!isInitialized()) return;
		t = new Thread(this,"THREAD_"+getClass().getName());
		t.start();
	}
	
	public void run()
	{
		try
		{
			button.setText("Stop");
			button.setIcon(iconStop);
			started();
			play();
		}
		catch(Exception e)
		{Outside.err(this,"run()",e);}
		
		button.setText("Play");
		button.setIcon(iconPlay);
		ended();
	}
	
	private void play() throws Exception
	{holder.p("play");}
	
	private void close() throws Exception
	{holder.p("close");}
	
	private boolean isPlaying()
	{return t!=null && t.isAlive();}
	
	private boolean isInitialized()
	{return holder!=null;}
	
	
	private void started()
	{send(this,"started()");}
	
	private void ended()
	{send(this,"ended()");}
	
	private void stopped()
	{send(this,"stopped()");}
}