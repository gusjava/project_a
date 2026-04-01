package a.entity.gus06.appli.gusclient1.gui.tool.entityview;

import a.framework.*;
import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20140815";}

	public static final long LAPSE = 500;


	private JPanel panel;

	private String entityName;
	private File entityFile;
	private JTextComponent comp;
	
	private Timer timer;
	private TimerTask task;

	
	
	public EntityImpl() throws Exception
	{
		panel = new JPanel(new BorderLayout());
	
		setSize(new Dimension(0,150));
		
		task = new TimerTask() {public void run() {updateGui();}};
		timer = new Timer("TIMER_"+getClass().getName());
		timer.schedule(task,new Date(),LAPSE);
	}
		
		
	private void setSize(Dimension d)
	{
		panel.setMaximumSize(d);
		panel.setMinimumSize(d);
		panel.setPreferredSize(d);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		entityName = (String) o[0];
		entityFile = (File) o[1];
		comp = (JTextComponent) o[2];
	}
	
	
	
		
	
	private void updateGui()
	{
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){updateGui_();}
		});
	}
	
	private void updateGui_()
	{
		
	}
}
