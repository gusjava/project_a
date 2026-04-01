package a.entity.gus06.sys.filetool.ext.textnav1.holder.list;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.util.List;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.util.Vector;
import javax.swing.ListCellRenderer;
import java.awt.Color;
import java.awt.Component;
import javax.swing.Icon;

public class EntityImpl extends S1 implements Entity, I, P, G, R, V, ListSelectionListener {

	public String creationDate() {return "20220427";}


	private JPanel panel;
	private JList list;
	private JLabel label;
	
	private List data;
	private Icon icon;


	public EntityImpl() throws Exception
	{
		list = new JList();
		list.setCellRenderer(new ListRenderer1());
		
		label = new JLabel(" ");
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(list), BorderLayout.CENTER);
		panel.add(label, BorderLayout.SOUTH);
	}
	
	public Object i() throws Exception
	{return panel;}
	
	
	public Object g() throws Exception
	{
		if(list.isSelectionEmpty()) return null;
		return list.getSelectedValue();
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("icon"))
		{
			icon = (Icon) obj;
			return;
		}
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("comp")) return list;
		if(key.equals("keys")) return new String[]{"comp"};
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		list.removeListSelectionListener(this);
		
		data = (List) obj;
		list.setListData(new Vector(data));
		label.setText(" "+data.size());
		
		list.addListSelectionListener(this);
	}
	
	
	public void valueChanged(ListSelectionEvent e)
	{selectionChanged();}
	
	
	private void selectionChanged()
	{send(this,"selectionChanged()");}
	
	
	
	private class ListRenderer1 extends JLabel implements ListCellRenderer
	{
		public ListRenderer1()
		{
			super();
			setOpaque(true);
		}

		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
		{
			Object[] row = (Object[]) value;
			String label = (String) row[0];
			
			setBackground(bg(isSelected));
			setText(label);
			setIcon(icon);
			return this;
		}
		
		private Color bg(boolean isSelected)
		{return isSelected?Color.LIGHT_GRAY:Color.WHITE;}
	}
}