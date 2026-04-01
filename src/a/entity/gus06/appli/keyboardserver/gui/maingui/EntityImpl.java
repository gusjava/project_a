package a.entity.gus06.appli.keyboardserver.gui.maingui;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EntityImpl implements Entity, ActionListener, I {

	public String creationDate() {return "20190413";}

	private Service manager;
	private Service tab;
	private Service persist;
	private Service debugGui;
	private Service pulseLabel;


	private JPanel panel;


	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.keyboardserver.manager");
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		persist = Outside.service(this,"gus06.swing.tabbedpane.persister.tab");
		debugGui = Outside.service(this,"*gus06.debug.gui.maingui");
		pulseLabel = Outside.service(this,"*gus06.swing.label.hold.eventpulse");
		
		tab.v("Debug",debugGui.i());
		tab.v("Pulse",pulseLabel.i());
		
		persist.v(getClass().getName()+"_tab",tab.i());
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) tab.i(),BorderLayout.CENTER);
		
		manager.addActionListener(this);
	}
	
	
	public Object i() throws Exception
	{return panel;}


	public void actionPerformed(ActionEvent e)
	{pulse();}
	
	
	private void pulse()
	{
		try{pulseLabel.e();}
		catch(Exception e)
		{Outside.err(this,"pulse()",e);}
	}

}
