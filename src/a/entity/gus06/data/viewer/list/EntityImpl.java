package a.entity.gus06.data.viewer.list;

import a.framework.*;
import javax.swing.*;
import java.util.*;
import java.awt.BorderLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class EntityImpl implements Entity, I, P, G, ListSelectionListener {

	public String creationDate() {return "20140731";}


	private Service viewer;
	private Service splitCust;
	private Service listRenderer;

	private JSplitPane split;
	private JList list;
	private JLabel label;
    
	private List data;


	public EntityImpl() throws Exception
	{
		viewer = Outside.service(this,"*gus06.data.viewer.object");
		splitCust = Outside.service(this,"gus06.swing.splitpane.cust.cust1");
		listRenderer = Outside.service(this,"gus06.swing.list.cust.renderer.obj2");
	
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
	}
	
	
	public Object i() throws Exception
	{return split;}
	
	
	public Object g() throws Exception
	{return data;}
	
	
	public void p(Object obj) throws Exception
	{
		data = (List) obj;
		if(data==null) resetGui();
		else updateGui();
	}
	
	
	private void updateGui() throws Exception
	{
		Vector vec = new Vector(data);
		list.setListData(vec);
		label.setText(" "+data.size());
		viewer.p(null);
	}
	
	
	private void resetGui() throws Exception
	{
		list.setListData(new Vector());
		label.setText(" ");
		viewer.p(null);
	}
		
	
	public void valueChanged(ListSelectionEvent e)
	{selectionChanged();}
    
    
    

	private void selectionChanged()
	{
		try
		{
			if(list.isSelectionEmpty()) {viewer.p(null);return;}
			Object value = list.getSelectedValue();
			viewer.p(value);
		}
		catch(Exception e)
		{Outside.err(this,"selectionChanged()",e);}
	}
}
