package a.entity.gus06.file.editor.ext.zip.entryviewer.panel.wav;

import a.framework.*;
import java.io.InputStream;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl extends S1 implements Entity, ActionListener, I, P, G, Runnable {

	public String creationDate() {return "20250524";}
	
	private Service play;
	
	private Icon iconPlay;
	private Icon iconStop;
	
	private JPanel panel;
	private JButton button;
	private G g;
	private P holder;
	private Thread t;

	public EntityImpl() throws Exception
	{
		play = Outside.service(this,"gus06.io.inputstream.wav.play");
		
		iconPlay = (Icon) Outside.resource(this,"icon#PLAY1_play");
		iconStop = (Icon) Outside.resource(this,"icon#PLAY1_stop");
		
		button = new JButton("Play");
		button.setIcon(iconPlay);
		button.addActionListener(this);
		
		panel = new JPanel(new BorderLayout());
		panel.add(button, BorderLayout.SOUTH);
	}
	
	public Object i() throws Exception
	{return panel;}
	
	
	public Object g() throws Exception
	{return g;}
	
	
	
	public void p(Object obj) throws Exception
	{
		if(obj instanceof String)
		{
			String s = (String) obj;
			if(s.equals("start")) {start();return;}
			if(s.equals("stop")) {reset();return;}
			throw new Exception("Unknown command: "+s);
		}
		if(obj instanceof G)
		{
			init((G) obj);
		}
	}


	public void actionPerformed(ActionEvent e)
	{shiftPlay();}
	
	
	
	private void shiftPlay()
	{
		if(isPlaying()) reset();
		else start();
	}
	
	
	private void init(G g) throws Exception
	{
		if(isPlaying()) reset();
		this.g = g;
		
		//formPanel.p(g); //NON SUPPPORTE
		
		if(g!=null)
		{
			holder = (P) play.t(g);
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