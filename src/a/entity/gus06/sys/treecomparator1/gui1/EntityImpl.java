package a.entity.gus06.sys.treecomparator1.gui1;

import a.framework.*;
import javax.swing.*;
import javax.swing.JLabel;
import java.util.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.BorderLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.ListCellRenderer;


public class EntityImpl implements Entity, I, P, ListSelectionListener {

	public String creationDate() {return "20190729";}
	
	public static final Color COLOR_SELECTION = new Color(244,244,244);


	private Service viewer;
	private Service splitCust;
	private Service compare;

	private JSplitPane split;
	private JList list;
	private JLabel label;
    
	private Map data;


	public EntityImpl() throws Exception
	{
		viewer = Outside.service(this,"*gus06.data.viewer.object");
		splitCust = Outside.service(this,"gus06.swing.splitpane.cust.cust1");
		compare = Outside.service(this,"gus06.sys.treecomparator1.compare");
	
		list = new JList();
		list.setCellRenderer(new ListRenderer0());
		list.addListSelectionListener(this);
        
		label = new JLabel(" ");
        
		JPanel p = new JPanel(new BorderLayout());
		p.add(new JScrollPane(list),BorderLayout.CENTER);
		p.add(label,BorderLayout.SOUTH);
        
		split = new JSplitPane();
		splitCust.p(split);
		
		split.setLeftComponent(p);
		split.setRightComponent((JComponent) viewer.i());
	}
	
	
	private boolean hasKey(Object key)
	{return data!=null && data.containsKey(key);}
	
	private Object getValue(Object key)
	{return hasKey(key) ? data.get(key) : null;}
	
	
	public Object i() throws Exception
	{return split;}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null) 
		{
			data = null;
			resetGui();
			return;
		}
		
		data = (Map) compare.t(obj);
		
		Vector vec = new Vector(data.keySet());
		Collections.sort(vec);
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
			Object key = list.getSelectedValue();
			Object value = getValue(key);
			viewer.p(value);
		}
		catch(Exception e)
		{Outside.err(this,"selectionChanged()",e);}
	}
	
	
	
	
	private class ListRenderer0 extends JLabel implements ListCellRenderer
	{
		public ListRenderer0()
		{
			super();
			setOpaque(true);
			setBackground(Color.WHITE);
			setFont(getFont().deriveFont(Font.PLAIN));
		}
		
		public Component getListCellRendererComponent(JList list, Object key, int index, boolean isSelected, boolean cellHasFocus)
		{
			setText(""+key);
			setForeground(findForeground(key));
			setBackground(isSelected ? COLOR_SELECTION : Color.WHITE);
			return this;
		}
		
		private Color findForeground(Object key)
		{
			Object value = getValue(key);
			if(value==null) return Color.GRAY;
			Object[] n = (Object[]) value;
			
			if(n[0]==null) return Color.GREEN.darker();
			if(n[1]==null) return Color.RED;
			return Color.ORANGE;
		}
	}
}
