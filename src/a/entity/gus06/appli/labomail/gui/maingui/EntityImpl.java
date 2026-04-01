package a.entity.gus06.appli.labomail.gui.maingui;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class EntityImpl implements Entity, ActionListener, I {

	public String creationDate() {return "20160606";}

	private Service tab;
	private Service connectGui;
	private Service storeImapGui;
	private Service storePop3Gui;
	private Service transportGui;
	private Service debugGui;
	private Service persist;
	
	private JPanel panel;


	public EntityImpl() throws Exception
	{
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		connectGui = Outside.service(this,"*gus06.appli.labomail.gui.connect");
		storeImapGui = Outside.service(this,"*gus06.appli.labomail.gui.store-1");
		storePop3Gui = Outside.service(this,"*gus06.appli.labomail.gui.store-2");
		transportGui = Outside.service(this,"*gus06.appli.labomail.gui.transport");
		debugGui = Outside.service(this,"*gus06.debug.gui.maingui");
		persist = Outside.service(this,"gus06.swing.tabbedpane.persister.tab");
		
		tab.v("GUI_connect#Connection",connectGui.i());
		tab.v("GUI_store#IMAP",storeImapGui.i());
		tab.v("GUI_store#POP3",storePop3Gui.i());
		tab.v("GUI_transport#SMTP",transportGui.i());
		tab.v("GUI_debug#Debug",debugGui.i());
		
		persist.v(getClass().getName()+"_tab",tab.i());
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) tab.i(),BorderLayout.CENTER);
		
		connectGui.addActionListener(this);
	}
	
	
	public Object i() throws Exception
	{return panel;}



	public void actionPerformed(ActionEvent e)
	{connected();}
	
	
	
	
	private void connected()
	{
		try
		{
			Object storeImap = connectGui.r("storeImap");
			Object storePop3 = connectGui.r("storePop3");
			Object transport = connectGui.r("transport");
			
			storeImapGui.p(storeImap);
			storePop3Gui.p(storePop3);
			transportGui.p(transport);
		}
		catch(Exception e)
		{Outside.err(this,"connected()",e);}
	}
	
}