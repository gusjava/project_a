package a.entity.gus06.appli.gusappmonitor.applitab.holder;

import a.framework.*;
import javax.swing.JPanel;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import javax.swing.JComponent;

public class EntityImpl implements Entity, ActionListener, P, I {

	public String creationDate() {return "20190410";}


	private Service propEditor;
	private Service infoViewer;
	private Service actionGui;
	private Service logGui;
	private Service lostGui;
	private Service debugGui;
	private Service tab;
	
	private JPanel panel;
	private R config;


	public EntityImpl() throws Exception
	{
		propEditor = Outside.service(this,"*gus06.data.editor.map.stringmap");
		infoViewer = Outside.service(this,"*gus06.data.viewer.map.stringmap");
		actionGui = Outside.service(this,"*gus06.appli.gusappmonitor.applitab.gui.action");
		logGui = Outside.service(this,"*gus06.appli.gusappmonitor.applitab.gui.log");
		lostGui = Outside.service(this,"*gus06.appli.gusappmonitor.applitab.gui.lost");
		debugGui = Outside.service(this,"*gus06.appli.gusappmonitor.applitab.gui.debug");
		
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		
		tab.v("Props",propEditor);
		tab.v("Infos",infoViewer);
		tab.v("Logs",logGui);
		tab.v("Lost history",lostGui);
		tab.v("Debug",debugGui);
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) tab.i(),BorderLayout.CENTER);
		panel.add((JComponent) actionGui.i(),BorderLayout.SOUTH);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		if(config!=null) ((S)config).removeActionListener(this);
		config = (R) obj;
		if(config!=null) ((S)config).addActionListener(this);
		
		actionGui.p(config);
		logGui.p(config);
		lostGui.p(config);
		debugGui.p(config);
		
		refresh();
		
	}


	public void actionPerformed(ActionEvent e)
	{refresh();}
	
	
	
	private void refresh()
	{
		try
		{
			Map propMap = (Map) config.r("propMap");
			Map infoMap = (Map) config.r("infoMap");
			
			propEditor.p(propMap);
			infoViewer.p(infoMap);
		}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}	
	}
}
