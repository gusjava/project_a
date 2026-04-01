package a.entity.gus06.sys.javaprojectviewer1.gui2;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;


public class EntityImpl implements Entity, P, I, Runnable {

	public String creationDate() {return "20170223";}


	private Service tab;
	private Service gui1;
	
	private Service buildHolder;

	private JPanel panel;
	
	private Object data;
	private Thread t;
	
	

	public EntityImpl() throws Exception
	{
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		gui1 = Outside.service(this,"*gus06.sys.javaprojectviewer1.gui2.jars");
		
		buildHolder = Outside.service(this,"gus06.sys.javaprojectviewer1.data.build.dataholder2");
		
		tab.v("Jars",gui1.i());
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) tab.i(),BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		if(t!=null && t.isAlive()) return;
		data = obj;
		
		t = new Thread(this,"THREAD_"+getClass().getName());
		t.start();
	}
	
	
	public void run()
	{
		try
		{
			if(data==null) return;
			
			R holder = (R) buildHolder.t(data);
			
			gui1.p(holder.r("jars"));
		}
		catch(Exception e)
		{Outside.err(this,"run()",e);}
	}
}
