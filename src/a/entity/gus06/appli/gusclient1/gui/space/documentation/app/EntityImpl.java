package a.entity.gus06.appli.gusclient1.gui.space.documentation.app;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class EntityImpl implements Entity, I, ListSelectionListener {

	public String creationDate() {return "20140809";}
	
	public static final String[] ITEMS = new String[]{
		"D_framework#Framework  ",
		"D_manager#Manager  ",
		"D_entity#Entities  ",
		"D_resource#Resources  "
	};
	
	

	private Service listRenderer;
	private Service shiftPanel;
	private Service splitCust;
	
	private Service gui_framework;
	private Service gui_manager;
	private Service gui_entities;
	private Service gui_resources;
	
	private JSplitPane split;
	private JList list;
	private Service[] s;



	public EntityImpl() throws Exception
	{
		listRenderer = Outside.service(this,"gus06.swing.list.cust.renderer.display");
		shiftPanel = Outside.service(this,"*gus06.swing.panel.shiftpanel");
		splitCust = Outside.service(this,"gus06.swing.splitpane.cust.cust1");
		
		gui_framework = Outside.service(this,"*gus06.appli.gusclient1.gui.appdoc.framework");
		gui_manager = Outside.service(this,"*gus06.appli.gusclient1.gui.appdoc.manager");
		gui_entities = Outside.service(this,"*gus06.appli.gusclient1.gui.appdoc.entities");
		gui_resources = Outside.service(this,"*gus06.appli.gusclient1.gui.appdoc.resources");
		
		s = new Service[]{gui_framework,gui_manager,gui_entities,gui_resources};
		
		
		split = new JSplitPane();
		splitCust.p(split);
		
		list = new JList(ITEMS);
    		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listRenderer.p(list);
		
		split.setLeftComponent(new JScrollPane(list));
		split.setRightComponent((JComponent) shiftPanel.i());
		
		list.addListSelectionListener(this);
	}
	
	
	
	public Object i() throws Exception
	{return split;}
	
	
	
	public void valueChanged(ListSelectionEvent e)
	{selectionChanged();}
	
	
	
	
	private void selectionChanged()
	{
		try
		{
			int index = list.getSelectedIndex();
			
			if(index==-1) shiftPanel.p(null);
			else shiftPanel.p(s[index]);
		}
		catch(Exception e)
		{Outside.err(this,"selectionChanged()",e);}
	}
}
