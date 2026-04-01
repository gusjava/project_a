package a.entity.gus06.appli.gusdbmanager.gui.tabbedpane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20150613";}
	
	public static final String ICONID_SERVER = "server";

	private Service cxManager;
	private Service panelManager;
	private Service closeable;
	private Service gui1;
	
	private JTabbedPane tab;
	
	

	public EntityImpl() throws Exception
	{
		cxManager = Outside.service(this,"gus06.appli.gusdbmanager.connection.manager");
		panelManager = Outside.service(this,"gus06.appli.gusdbmanager.connection.panelmanager");
		gui1 = Outside.service(this,"*gus06.appli.gusdbmanager.gui.gui1");
		
		closeable = Outside.service(this,"*gus06.swing.tabbedpane.build.closeable");
		
		closeable.v(ICONID_SERVER+"#Servers",gui1.i());
		closeable.v("acceptClose",panelManager);
		
		
		cxManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{connectionModified();}
		});
		closeable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{tabClosed();}
		});
		
		tab = (JTabbedPane) closeable.i();
	}



	public Object i() throws Exception
	{return tab;}
	
	
	
	
	private Object find(Object obj) throws Exception
	{return panelManager.t(obj);}
	
	
	
	
	
	private void connectionModified()
	{
		try
		{
			F holder = (F) cxManager.r("lastModified");
			boolean connected = holder.f(null);
			if(!connected) return;

			Object panel = find(holder);
			if(!closeable.f(panel))
			{
				String display = (String) ((R) holder).r("display");
				closeable.v("*"+display,panel);
			}
		}
		catch(Exception e)
		{Outside.err(this,"connectionModified()",e);}
	}

	
	
	
	
	
	private void tabClosed()
	{
		try
		{
			Object panel = closeable.r("removedComp");
			V holder = (V) find(panel);
			holder.v("clear",null);
		}
		catch(Exception e)
		{Outside.err(this,"tabClosed()",e);}
	}
}
