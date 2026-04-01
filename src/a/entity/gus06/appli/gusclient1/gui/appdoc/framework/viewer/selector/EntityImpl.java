package a.entity.gus06.appli.gusclient1.gui.appdoc.framework.viewer.selector;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.HashMap;


public class EntityImpl extends S1 implements Entity, I, G, ListSelectionListener {

	public String creationDate() {return "20140829";}
	
	public static final String ICONID = "UTIL_javasrc";

	public static final String[] KEYS = new String[] {
		"Entity",
		"Outside",
		"Manager",
		"Service",
		"Impl S1",
		"Feature E",
		"Feature P",
		"Feature G",
		"Feature V",
		"Feature R",
		"Feature T",
		"Feature F",
		"Feature H",
		"Feature I",
		"Feature S",
	};


	private Service buildJList;
	
	private HashMap map;

	private JScrollPane scroll;
	private JList list;
	
	


	public EntityImpl() throws Exception
	{
		buildJList = Outside.service(this,"gus06.swing.list.build.fromicon");
		
		map = new HashMap();
		
		put(0,"Entity");
		put(1,"Outside");
		put(2,"Manager");
		put(3,"Service");
		put(4,"S1");
		put(5,"E");
		put(6,"P");
		put(7,"G");
		put(8,"V");
		put(9,"R");
		put(10,"T");
		put(11,"F");
		put(12,"H");
		put(13,"I");
		put(14,"S");
		
		list = (JList) buildJList.t(ICONID);
		list.setListData(KEYS);
		
		list.addListSelectionListener(this);
		
		scroll = new JScrollPane(list);
	}
	
	
	private void put(int index, String name)
	{map.put(KEYS[index],"gus06.framework."+name);}
	
	
	
	public Object g() throws Exception
	{
		String key = (String) list.getSelectedValue();
		return map.get(key);
	}
	
	
	
	public Object i() throws Exception
	{return scroll;}
	


	public void valueChanged(ListSelectionEvent e)
	{selectionChanged();}



	private void selectionChanged()
	{send(this,"selectionChanged()");}
}
