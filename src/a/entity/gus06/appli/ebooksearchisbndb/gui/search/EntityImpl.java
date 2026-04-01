package a.entity.gus06.appli.ebooksearchisbndb.gui.search;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Map;

public class EntityImpl implements Entity, ActionListener, I, Runnable {

	public String creationDate() {return "20150509";}


	private Service topPanel;
	private Service viewer;
	private Service perform;

	private JPanel panel;
	private Thread t;
	
	

	public EntityImpl() throws Exception
	{
		topPanel = Outside.service(this,"*gus06.appli.ebooksearchisbndb.gui.search.top");
		viewer = Outside.service(this,"*gus06.data.viewer.string.json");
		perform = Outside.service(this,"gus06.web.isbndb.api.perform");
		
		topPanel.addActionListener(this);
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) topPanel.i(),BorderLayout.NORTH);
		panel.add((JComponent) viewer.i(),BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}


	public void actionPerformed(ActionEvent e)
	{start();}
	
	
	
	private void start()
	{
		if(t!=null && t.isAlive()) return;
		t = new Thread(this,"THREAD_"+getClass().getName());
		t.start();
	}
	
	
	
	public void run()
	{
		try
		{
			viewer.p(null);
			Map map = (Map) topPanel.g();
			
			if(map!=null)
			{
				String r = (String) perform.t(map);
				viewer.p(r);
			}
		}
		catch(Exception e)
		{Outside.err(this,"run()",e);}
	}

}
