package a.entity.gus06.sys.filemanagement1.gui.detailpanel.file.doubloons.resultpanel;

import a.framework.*;
import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.ListSelectionModel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Color;
import java.util.List;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class EntityImpl extends S1 implements Entity, I, P, V, ListSelectionListener {

	public String creationDate() {return "20250610";}


	public static final Color SELECTION_COLOR = new Color(234,234,234);
	public static final int COL_NUMBER = 5;
	
	
	private Service findIcon;
	private Service tableTooltip;
	private Service dataSize;
	private Service buildMap;
	private Service format;
	
	private Icon iconRoot;
	private Icon iconDir;
	
	private JPanel panel;
	private JTable table;
	private JScrollPane scroll;
	private TableModel0 model;
	
	private JLabel labelNb;
	
	private List results;
	private List resultsF;
	
	


	public EntityImpl() throws Exception
	{
		findIcon = Outside.service(this,"gus06.file.filename.icon.t1");
		tableTooltip = Outside.service(this,"gus06.swing.table.cust.tooltip2");
		dataSize = Outside.service(this,"gus06.string.transform.format.datasize.en");
		buildMap = Outside.service(this,"gus06.sys.filemanagement1.gui.gui1_3.search.buildmap");
		format = Outside.service(this,"gus06.sys.filemanagement1.gui.gui1_3.search.resultpanel.format");
		
		iconRoot = (Icon) Outside.resource(this,"icon#UTIL_disk");
		iconDir = (Icon) Outside.resource(this,"icon#dir");
		
		model = new TableModel0();
		
		table = new JTable(model);
		table.setGridColor(Color.LIGHT_GRAY);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		table.setDefaultRenderer(String.class,new TableCellRenderer0());
		table.getSelectionModel().addListSelectionListener(this);
		
		initColumnSize(3,80);
		initColumnSize(4,25);
		
		tableTooltip.p(table);
		
		scroll = new JScrollPane(table);
		scroll.getViewport().setBackground(Color.WHITE);
		scroll.getViewport().setOpaque(true);

		labelNb = new JLabel(" ");
		
		panel = new JPanel(new BorderLayout());
		panel.add(scroll,BorderLayout.CENTER);
		panel.add(labelNb,BorderLayout.SOUTH);
	}
	
	
	
	
	private void initColumnSize(int index, int size)
	{
		table.getTableHeader().getColumnModel().getColumn(index).setMinWidth(size);
		table.getTableHeader().getColumnModel().getColumn(index).setMaxWidth(size);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public Object g() throws Exception
	{
		int index = table.getSelectedRow();
		if(index==-1) return null;
		if(results==null) return null;
		
		String[] result = (String[]) results.get(index);
		return buildMap.t(result);
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		results = (List) obj;
		resultsF = (List) format.t(obj);
		
		refresh();
	}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("select")) {select((String) obj);return;}
		throw new Exception("Unknown key: "+key);
	}
	
	
	private void refresh()
	{
		labelNb.setText(getNbDisplay());
		model.fireTableDataChanged();
	}
	
	
	private int getNb()
	{return results!=null ? results.size() : 0;}
	
	
	private String getNbDisplay()
	{
		int nb = getNb();
		if(nb==0) return " ";
		if(nb==1) return " 1 result";
		return " "+nb+" results";
	}
	
	
	
	private void select(String info) throws Exception
	{
		if(info.equals("first"))
		{
			table.setRowSelectionInterval(0,0);
		}
		else throw new Exception("Unsupported select info: "+info);
	}
	
	
	
	
	private class TableModel0 extends AbstractTableModel
	{
		public int getColumnCount() {return COL_NUMBER;}
		public int getRowCount() {return getNb();}
		public Class getColumnClass(int y) {return String.class;}
		
		public String getColumnName(int y)
		{
			if(y==0) return "root";
			if(y==1) return "location";
			if(y==2) return "file name";
			if(y==3) return "size";
			if(y==4) return "N";
			return null;
		}

		public Object getValueAt(int x, int y)
		{
			if(resultsF==null) return null;
			if(resultsF.size()<=x) return null;
			String[] r = (String[]) resultsF.get(x);
			return r[y];
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
			String display = buildDisplay((String) value,column);
			Icon icon = buildIcon((String) value,column);
			
			Color bgColor = isSelected ? SELECTION_COLOR : Color.WHITE;
			
			setText(display);
			setBackground(bgColor);
			setIcon(icon);
			return this;
		}
	}
	
	
	
	private String buildDisplay(String value, int column)
	{
		if(value==null) return "";
		if(column==0) return value;
		if(column==1) return value;
		if(column==2) return value;
		if(column==3) return sizeDisplay(value);
		if(column==4) return value;
		
		return "";
	}
	
	private Icon buildIcon(String value, int column)
	{
		if(value==null) return null;
		if(column==0) return iconRoot;
		if(column==1) return iconDir;
		if(column==2) return fileIcon(value);
		if(column==3) return null;
		if(column==4) return null;
		
		return null;
	}
	
	
	private String sizeDisplay(String s)
	{
		try{return (String) dataSize.t(s);}
		catch(Exception e) {return "###"+s;}
	}
	
	private Icon fileIcon(String s)
	{
		try{return (Icon) findIcon.t(s);}
		catch(Exception e) {return null;}
	}
	
	
	public void valueChanged(ListSelectionEvent e)
	{selectionChanged();}
	
	
	private void selectionChanged()
	{send(this,"selectionChanged()");}
}