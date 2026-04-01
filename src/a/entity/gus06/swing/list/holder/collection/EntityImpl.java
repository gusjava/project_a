package a.entity.gus06.swing.list.holder.collection;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.Collection;
import java.util.Vector;
import java.util.Collections;
import java.util.List;


public class EntityImpl extends S1 implements Entity, I, P, G, R, ListSelectionListener {

	public String creationDate() {return "20150329";}

	private JList list;
	private Collection col;

	public EntityImpl() throws Exception
	{
		list = new JList();
		list.addListSelectionListener(this);
	}
	
	
	public Object i() throws Exception
	{return list;}
	
	
	
	public void p(Object obj) throws Exception
	{
		col = (Collection) obj;
		
		if(col!=null)
		{
			Vector vec = new Vector(col);
			if(!(col instanceof List))
			Collections.sort(vec);
			list.setListData(vec);
		}
		else
		{
			list.setListData(new Vector());
		}
	}
	
	
	
	public Object g() throws Exception
	{
		if(list.isSelectionEmpty()) return null;
		return list.getSelectedValue();
	}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("selectedIndex")) return selectedIndex();
		if(key.equals("selectedIndices")) return selectedIndices();
		
		if(key.equals("keys")) return new String[]{"selectedIndex","selectedIndices"};
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	private Integer selectedIndex()
	{
		if(list.isSelectionEmpty()) return null;
		return list.getSelectedIndex();
	}
	
	private int[] selectedIndices()
	{
		if(list.isSelectionEmpty()) return null;
		return list.getSelectedIndices();
	}
	
	
	
	public void valueChanged(ListSelectionEvent e)
	{selectionChanged();}
	
	private void selectionChanged()
	{send(this,"selectionChanged()");}
}