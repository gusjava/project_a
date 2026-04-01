package a.entity.gus06.data.viewer.map.iconmap;

import a.framework.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.util.*;
import java.awt.Color;
import java.awt.Component;


public class EntityImpl implements Entity, P, I, R {

	public String creationDate() {return "20140910";}
	
	public static final Color DEFAULT_COLOR = new Color(153,204,255);
	

	private JPanel panel;
	private JList list;
	private JLabel label;
	
	private Map map;
	
	
	
	public EntityImpl() throws Exception
	{
		list = new JList();
		list.setCellRenderer(new ListRenderer1());
		
		label = new JLabel(" ");
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(list),BorderLayout.CENTER);
		panel.add(label,BorderLayout.SOUTH);
	}
	
	
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		if(map==null) resetGui();
		else updateGui();
	}
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("list")) return list;
		if(key.equals("keys")) return new String[]{"list"};
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	private void resetGui()
	{
		list.setListData(new Vector());
		label.setText(" ");
	}
	
	
	private void updateGui() throws Exception
	{
		list.setListData(buildKeys());
		label.setText(" Number: "+map.size());
	}
	
	
	
	private Vector buildKeys()
	{
		if(map==null) return new Vector();
		Vector keys = new Vector(map.keySet());
		Collections.sort(keys);
		return keys;
	}
	
	
	private Icon icon(String key)
	{
		if(map==null) return null;
		return (Icon) map.get(key);
	}
	
	
	
	
	private class ListRenderer1 extends JLabel implements ListCellRenderer
	{
		public ListRenderer1()
		{setOpaque(true);}

		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
		{
			String key = (String) value;
			setIcon(icon(key));
			setText(key);
			setBackground(bg(isSelected));
			return this;
		}
		
		private Color bg(boolean isSelected)
		{return isSelected?DEFAULT_COLOR:Color.WHITE;}
	}
}
