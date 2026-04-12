package a.entity.gus06.data.viewer.map;

import a.framework.*;
import javax.swing.*;
import java.util.*;
import java.awt.BorderLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, I, P, G, ListSelectionListener, ActionListener {

	public String creationDate() {return "20140731";}

	private Service viewer;
	private Service supHolder;
	private Service splitCust;
	private Service listRenderer;
	private Service tryAndSort;

	private JSplitPane split;
	private JList list;
	private JLabel label;
    
	private Map data;

	public EntityImpl() throws Exception
	{
		viewer = Outside.service(this,"*gus06.data.viewer.object");
		supHolder = Outside.service(this,"*gus06.support.holder");
		splitCust = Outside.service(this,"gus06.swing.splitpane.cust.cust1");
		listRenderer = Outside.service(this,"gus06.swing.list.cust.renderer.obj1");
		tryAndSort = Outside.service(this,"gus06.data.list.tryandsort");
	
		list = new JList();
		list.addListSelectionListener(this);
		listRenderer.p(list);
        
		label = new JLabel(" ");
        
		JPanel p = new JPanel(new BorderLayout());
		p.add(new JScrollPane(list),BorderLayout.CENTER);
		p.add(label,BorderLayout.SOUTH);
        
		split = new JSplitPane();
		splitCust.p(split);
		
		split.setLeftComponent(p);
		split.setRightComponent((JComponent) viewer.i());
		
		supHolder.addActionListener(this);
	}
	
	
	public Object i() throws Exception
	{return split;}
	
	
	public Object g() throws Exception
	{return data;}
	
	
	public void p(Object obj) throws Exception
	{
		data = (Map) obj;
		supHolder.p(data);
	
		if(data==null)
		{
			list.setListData(new Vector());
			label.setText(" ");
			viewer.p(null);
		}
		else
		{
			Vector vec = mapKeys();
			tryAndSort.p(vec);
			list.setListData(vec);
			label.setText(" "+data.size());
			viewer.p(null);
		}
	}
	
	private void updateGui()
	{
		try
		{
			Vector vec = mapKeys();
			tryAndSort.p(vec);
			list.setListData(vec);
			label.setText(" "+data.size());
			viewer.p(null);
		}
		catch(Exception e)
		{Outside.err(this,"updateGui()",e);}
	}
	
	public void valueChanged(ListSelectionEvent e)
	{selectionChanged();}
	
	
	private void selectionChanged()
	{
		try
		{
			if(list.isSelectionEmpty()) {viewer.p(null);return;}
			Object k = list.getSelectedValue();
			viewer.p(data.get(k));
		}
		catch(Exception e)
		{Outside.err(this,"selectionChanged()",e);}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		updateGui();
	}
	
	// ignore synchronization failure
	private Vector mapKeys()
	{
		try
		{
			if(data==null) return new Vector();
			return new Vector(data.keySet());
		}
		catch(Throwable t)
		{return new Vector();}
	}
}