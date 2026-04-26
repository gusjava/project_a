package a.entity.gus06.app.icon.gui.viewer;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.JScrollPane;
import java.util.Set;
import java.util.HashSet;
import javax.swing.JLabel;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import javax.swing.Icon;
import java.awt.Color;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingUtilities;

public class EntityImpl extends S1 implements Entity, I, E, G, R, V, ListSelectionListener, ActionListener {

	public String creationDate() {return "20191030";}
	
	public static final Color SELECTION_COLOR = new Color(210,235,235);


	private Service outsideIcons;
	private Service insideIcons;
	private Service onF5;
	private Service fieldHolder;
	private Service tableLinker;
	private Service listFilter;


	private JPanel panel;
	private JTextField field;
	private JLabel label;
	
	private JTable table;
	private TableModel0 model;
	
	private Map mapDir;
	private Map mapJar;
	
	private List keys1;
	private List keys;
	

	public EntityImpl() throws Exception
	{
		outsideIcons = Outside.service(this,"gus06.icon.loader.outside.map");
		insideIcons = Outside.service(this,"gus06.app.jarfile.listing.resources.iconmap.gyem");
		onF5 = Outside.service(this,"gus06.swing.comp.cust3.execute.f5");
		fieldHolder = Outside.service(this,"*gus06.data.editor.string.textfield.editor1");
		tableLinker = Outside.service(this,"gus.x.swing.table.textfield.linker");
		listFilter = Outside.service(this,"gus06.list.filter.rule.all");
		
		field = (JTextField) fieldHolder.i();
		fieldHolder.addActionListener(this);
		
		label = new JLabel(" ");
		
		model = new TableModel0();
		table = new JTable(model);
		table.setDefaultRenderer(String.class,new TableCellRenderer0());
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(this);
		
		onF5.p(new Object[]{table,this});
		
		resizeColumns(1,30);
		resizeColumns(2,30);
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) fieldHolder.i(),BorderLayout.NORTH);
		panel.add(new JScrollPane(table),BorderLayout.CENTER);
		panel.add(label,BorderLayout.SOUTH);
		
		tableLinker.p(new Object[]{table,field});
		
		e();
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public Object g() throws Exception
	{
		int row = table.getSelectedRow();
		return row>=0 ? table.getValueAt(row,0) : null;
	}

	
	
	public void e() throws Exception
	{
		mapDir = (Map) outsideIcons.g();
		mapJar = (Map) insideIcons.g();
		
		Set set = new HashSet(mapDir.keySet());
		set.addAll(mapJar.keySet());
		
		keys1 = new ArrayList(set);
		Collections.sort(keys1);
		refresh();
	}
	
	private void refresh()
	{
		try
		{
			String query = (String) fieldHolder.g();
			keys = (List) listFilter.t(new Object[]{keys1,query});
			
			label.setText(labelDisplay());
			model.fireTableDataChanged();
		}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}
	
	private String labelDisplay()
	{
		int nb = keys.size();
		int nb1 = keys1.size();
		return nb!=nb1 ? " "+nb+"/"+nb1 : " "+nb;
	}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("select")) {select((String) obj);return;}
		if(key.equals("search")) {search((String) obj);return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("field")) return field;
		if(key.equals("table")) return table;
		
		if(key.equals("keys")) return new String[]{"field","table"};
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	private Icon iconFor(Map map, String key)
	{return map.containsKey(key) ? (Icon) map.get(key) : null;}
	
	
	private void resizeColumns(int column, int length)
	{
		table.getColumnModel().getColumn(column).setMinWidth(length);
		table.getColumnModel().getColumn(column).setMaxWidth(length);
	}
	
	
	
	
	private void select(String key) throws Exception
	{
		if(keys==null) return;
		
		int index = keys.indexOf(key);
		if(index==-1) table.clearSelection();
		else
		{
			table.setRowSelectionInterval(index,index);
			table.scrollRectToVisible(new Rectangle(table.getCellRect(index,0,true)));
		}
	}
	
	
	private void search(String v)
	{
		try
		{
			fieldHolder.p(v);
		}
		catch(Exception e)
		{Outside.err(this,"search(String)",e);}
	}

	
	
	
	private class TableModel0 extends AbstractTableModel
	{
		public int getColumnCount()
		{return 3;}
		
		public int getRowCount()
		{return keys==null?0:keys.size();}
		
		public String getColumnName(int y)
		{
			if(y==0) return "ID";
			if(y==1) return "jar";
			return "dir";
		}
		
		public Class getColumnClass(int y)
		{return String.class;}

		public Object getValueAt(int x, int y)
		{
			if(keys==null) return null;
			return keys.get(x);
		}
	}
	
	
	
	
	private class TableCellRenderer0 extends JLabel implements TableCellRenderer
	{
		public TableCellRenderer0()
		{
			super();
			setOpaque(true);
    			setBackground(Color.WHITE);
    		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		{
			String key = (String) value;
			if(column==0)
			{
				setText(key);
				setIcon(null);
			}
			else if(column==1)
			{
				setText("");
				setIcon(iconFor(mapJar,key));
			}
			else if(column==2)
			{
				setText("");
				setIcon(iconFor(mapDir,key));
			}
			setBackground(isSelected ? SELECTION_COLOR : Color.WHITE);
			return this;
		}
	}
	
	
	
	public void valueChanged(ListSelectionEvent e)
	{selected();}
	
	private void selected()
	{send(this,"selected()");}



	public void actionPerformed(ActionEvent e)
	{
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {refresh();}
		});
	}
}