package a.entity.gus06.sys.jdbcbrowser.gui.list;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.Map;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import java.awt.Component;
import java.awt.Color;
import java.util.Vector;
import javax.swing.event.ListSelectionEvent;
import java.util.Collections;

public class EntityImpl extends S1 implements Entity, I, P, G, ListSelectionListener {

	public String creationDate() {return "20190517";}


	private JPanel panel;
	private JList list;
	private JLabel labelNumber;
	
	private Map map;
	
	
	public EntityImpl() throws Exception
	{
		list = new JList();
		list.setCellRenderer(new ListRenderer1());
		list.addListSelectionListener(this);
		
		labelNumber = new JLabel(" ");
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(list),BorderLayout.CENTER);
		panel.add(labelNumber,BorderLayout.SOUTH);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		
		if(map!=null)
		{
			Vector vec = new Vector(map.keySet());
			Collections.sort(vec);
			list.setListData(vec);
			labelNumber.setText(" "+vec.size()+" tables");
		}
		else
		{
			list.setListData(new Vector());
			labelNumber.setText(" ");
		}
	}

	
	
	public Object g() throws Exception
	{
		if(list.isSelectionEmpty()) return null;
		return list.getSelectedValue();
	}
	
	
	public void valueChanged(ListSelectionEvent e)
	{selectionChanged();}
	
	private void selectionChanged()
	{send(this,"selectionChanged()");}
	
	
	
	private Integer getCount(String table)
	{
		if(map==null) return 0;
		if(!map.containsKey(table)) return 0;
		return (Integer) map.get(table);
	}
	
	
	
	private class ListRenderer1 extends JLabel implements ListCellRenderer
	{
		public ListRenderer1()
		{
			super();
			setOpaque(true);
		}

		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
		{
			String table = (String) value;
			Integer count = getCount(table);
			
			setBackground(bg(isSelected));
			setText(table+" ["+count+"]");
			return this;
		}
		
		private Color bg(boolean isSelected)
		{return isSelected?Color.LIGHT_GRAY:Color.WHITE;}
	}
}
