package a.entity.gus06.sys.filetool.ext.runtask1.holder;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Map;
import java.util.Set;
import java.util.Collections;
import java.util.HashSet;
import java.io.File;

public class EntityImpl implements Entity, ActionListener, I, P, Runnable {

	public String creationDate() {return "20150619";}
	
	public static final String KEY_RUNTASK = "runtask";


	private Service progress;
	private Service findRoot;
	private Service unique;
	
	private JPanel panel;
	private JButton button;
	
	private Map map;
	private Thread t;
	private Set interrupt;
	


	public EntityImpl() throws Exception
	{
		progress = Outside.service(this,"*gus06.swing.progressbar.progress1a");
		findRoot = Outside.service(this,"gus06.sys.filetool.findroot");
		unique = Outside.service(this,"entityunique");
		
		button = new JButton("Run task");
		button.addActionListener(this);
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) progress.i(),BorderLayout.NORTH);
		panel.add(button,BorderLayout.SOUTH);
		
		interrupt = Collections.synchronizedSet(new HashSet());
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
	}
	


	public void actionPerformed(ActionEvent e)
	{startTask();}
	
	
	
	private void startTask()
	{
		try
		{
			if(t!=null && t.isAlive()) return;
			t = new Thread(this,"THREAD_"+getClass().getName());
			t.start();
		}
		catch(Exception e)
		{Outside.err(this,"startTask()",e);}
	}
	
	
	
	
	public void run()
	{
		button.setEnabled(false);
		perform();
		button.setEnabled(true);
	}
	
	
	
	
	private void perform()
	{
		try
		{
			P p = (P) unique.t(get(KEY_RUNTASK));
			File dir = (File) findRoot.t(map);
			p.p(new Object[]{map,dir,progress,interrupt});
		}
		catch(Exception e)
		{Outside.err(this,"perform()",e);}
	}

	
	
	
	
	
	private String get(String key) throws Exception
	{
		if(map==null) throw new Exception("Map not initialized yet");
		if(!map.containsKey(key)) throw new Exception("Key not found inside tool: "+key);
		return (String) map.get(key);
	}
}
