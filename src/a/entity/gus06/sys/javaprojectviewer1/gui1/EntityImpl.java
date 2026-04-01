package a.entity.gus06.sys.javaprojectviewer1.gui1;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;


public class EntityImpl implements Entity, P, I, Runnable {

	public String creationDate() {return "20170223";}


	private Service tab;
	private Service gui1;
	private Service gui2;
	private Service gui3;
	private Service gui4;
	
	private Service buildHolder;

	private JPanel panel;
	
	private Object data;
	private Thread t;
	
	

	public EntityImpl() throws Exception
	{
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		gui1 = Outside.service(this,"*gus06.sys.javaprojectviewer1.gui1.root");
		gui2 = Outside.service(this,"*gus06.sys.javaprojectviewer1.gui1.package1");
		gui3 = Outside.service(this,"*gus06.sys.javaprojectviewer1.gui1.class1");
		gui4 = Outside.service(this,"*gus06.sys.javaprojectviewer1.gui1.import1");
		
		buildHolder = Outside.service(this,"gus06.sys.javaprojectviewer1.data.build.dataholder1");
		
		tab.v("Roots",gui1.i());
		tab.v("Packages",gui2.i());
		tab.v("Classes",gui3.i());
		tab.v("Imports",gui4.i());
		
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
			
			gui1.p(holder.r("rootsMap"));
			gui2.p(holder.r("packagesMap"));
			gui3.p(holder.r("classpathsMap"));
			gui4.p(holder.r("importsMap"));
		}
		catch(Exception e)
		{Outside.err(this,"run()",e);}
	}
}
