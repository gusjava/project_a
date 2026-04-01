package a.entity.gus06.appli.gusclient1.gui.appdoc.manager.viewer.main.selector;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.*;


public class EntityImpl extends S1 implements Entity, I, G, ListSelectionListener {

	public String creationDate() {return "20140830";}
	
	public static final String ICONID = "UTIL_javasrc";

	
	
	private Service findList;
	private Service buildJList;
	
	private List keys;

	private JScrollPane scroll;
	private JList list;
	
	


	public EntityImpl() throws Exception
	{
		findList = Outside.service(this,"gus06.app.jarfile.listing.java.manager.gyem.groups");
		buildJList = Outside.service(this,"gus06.swing.list.build.fromicon");
		
		keys = (List) findList.r("mainClasses");
		
		list = (JList) buildJList.t(ICONID);
		list.setListData(new Vector(keys));
		list.addListSelectionListener(this);
		
		scroll = new JScrollPane(list);
	}
	
	
	
	public Object g() throws Exception
	{
		String value = (String) list.getSelectedValue();
		return "gus06.manager.gus.gyem."+value;
	}
	
	
	
	public Object i() throws Exception
	{return scroll;}
	


	public void valueChanged(ListSelectionEvent e)
	{selectionChanged();}



	private void selectionChanged()
	{send(this,"selectionChanged()");}
}
