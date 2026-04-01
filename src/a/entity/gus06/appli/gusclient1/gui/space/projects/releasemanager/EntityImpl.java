package a.entity.gus06.appli.gusclient1.gui.space.projects.releasemanager;

import a.framework.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;


public class EntityImpl implements Entity, ActionListener, I, ListSelectionListener {

	public String creationDate() {return "20140901";}


	private Service listRenderer;
	private Service release;
	private Service manager;
	private Service idToFile;
	private Service splitCust;
	private Service releaseGui;
	
	
	
	private JSplitPane split;
	private JList list;
	
	
	public EntityImpl() throws Exception
	{
		listRenderer = Outside.service(this,"gus06.swing.list.cust.renderer.file");
		release = Outside.service(this,"gus06.appli.gusclient1.project.release");
		manager = Outside.service(this,"gus06.appli.gusclient1.project.manager");
		idToFile = Outside.service(this,"gus06.appli.gusclient1.project.idtodir.release");
		splitCust = Outside.service(this,"gus06.swing.splitpane.cust.cust1");
		releaseGui = Outside.service(this,"*gus06.appli.gusclient1.gui.space.projects.releasemanager.panel");
		
		list = new JList();
		listRenderer.p(list);
		
		split = new JSplitPane();
		splitCust.p(split);
		
		split.setLeftComponent(new JScrollPane(list));
		split.setRightComponent((JComponent) releaseGui.i());
		
		manager.addActionListener(this);
		release.addActionListener(this);
		list.addListSelectionListener(this);
		
		updateGui();
	}
	
	
	public Object i() throws Exception
	{return split;}


	public void actionPerformed(ActionEvent e)
	{updateGui();}
	
	
	
	public void valueChanged(ListSelectionEvent e)
	{selectionChanged();}
	
	
	
	private void selectionChanged()
	{
		try
		{
			File dir = (File) list.getSelectedValue();
			releaseGui.p(dir);
		}
		catch(Exception e)
		{Outside.err(this,"selectionChanged()",e);}
	}
	
	
	
	
	private void updateGui()
	{
		try
		{
			releaseGui.p(null);
		
			String id = (String) manager.g();	
			if(id==null)
			{
				list.setListData(new Object[]{});
			}
			else
			{
				File dir = (File) idToFile.t(id);
				File[] f = dir.listFiles();
				list.setListData(f);
			}
		}
		catch(Exception e)
		{Outside.err(this,"updateGui()",e);}
	}

}
