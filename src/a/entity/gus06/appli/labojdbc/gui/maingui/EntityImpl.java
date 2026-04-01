package a.entity.gus06.appli.labojdbc.gui.maingui;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class EntityImpl implements Entity, ActionListener, I {

	public String creationDate() {return "20150621";}


	private Service tab;
	private Service connectGui;
	private Service viewGui;
	private Service userGui;
	private Service sqlGui;
	private Service varGui;
	private Service infoGui;
	private Service debugGui;
	private Service persistTab;
	private Service persistArea;
	
	private JPanel panel;
	
	
	

	public EntityImpl() throws Exception
	{
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		connectGui = Outside.service(this,"*gus06.appli.labojdbc.gui.connect");
		viewGui = Outside.service(this,"*gus06.jdbc.gui.cx1");
		userGui = Outside.service(this,"*gus06.jdbc.gui.user1");
		sqlGui = Outside.service(this,"*gus06.jdbc.gui.sqlquery1");
		varGui = Outside.service(this,"*gus06.jdbc.gui.var1");
		infoGui = Outside.service(this,"*gus06.jdbc.gui.infoarea1");
		debugGui = Outside.service(this,"*gus06.debug.gui.maingui");
		persistTab = Outside.service(this,"gus06.swing.tabbedpane.persister.tab");
		persistArea = Outside.service(this,"gus06.swing.textcomp.persister.text");
		
		tab.v("GUI_connect#Connection",connectGui.i());
		tab.v("GUI_view#Databases",viewGui.i());
		tab.v("GUI_user#Users",userGui.i());
		tab.v("GUI_variable#Variables",varGui.i());
		tab.v("GUI_info#Informations",infoGui.i());
		tab.v("GUI_sql#SQL query",sqlGui.i());
		tab.v("GUI_debug#Debug",debugGui.i());
		
		Object inputArea = sqlGui.r("inputArea");
		
		persistTab.v(getClass().getName()+"_tab",tab.i());
		persistArea.v(getClass().getName()+"_inputArea",inputArea);
		
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
			Object holder = connectGui.g();
			
			viewGui.p(holder);
			userGui.p(holder);
			sqlGui.p(holder);
			varGui.p(holder);
			infoGui.p(holder);
		}
		catch(Exception e)
		{Outside.err(this,"connected()",e);}
	}

}
