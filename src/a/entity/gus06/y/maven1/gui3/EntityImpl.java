package a.entity.gus06.y.maven1.gui3;

import a.framework.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.ListSelectionModel;
import java.awt.Color;
import java.util.List;

public class EntityImpl implements Entity, I, V, R, G, ActionListener {

	public String creationDate() {return "20251220";}

	private Service tableTooltip;
	private Service clearCopyPasteCut;
	private Service sortTable;
	private Service autoScroll;

	private JPanel panel;
	private JTable table;
	private JScrollPane scroll;
	
	private TableModel0 tableModel;
	private List list;
	
	
	private Object engine;

	public EntityImpl() throws Exception
	{
		tableTooltip = Outside.service(this, "gus.x.swing.table.cust.tooltip1");
		clearCopyPasteCut = Outside.service(this, "gus.x.swing.comp.action.clear.copypastecut");
		sortTable = Outside.service(this,"gus06.swing.table.cust.sort2");
		autoScroll = Outside.service(this,"gus06.swing.scroll.autoposition1");
		
		tableModel = new TableModel0();
		
		table = new JTable(tableModel);
		table.setShowGrid(false);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		clearCopyPasteCut.p(table);
		tableTooltip.p(table);
		sortTable.p(table);
		
		scroll = new JScrollPane(table);
		scroll.getViewport().setBackground(Color.WHITE);
		scroll.getViewport().setOpaque(true);
		autoScroll.p(scroll);
		
		panel = new JPanel(new BorderLayout());
		
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	public Object g() throws Exception
	{return null;}
	
	
	public Object r(String key) throws Exception
	{
		throw new Exception("Unknown key: "+key);
	}
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("engine"))
		{
			if(engine!=null) ((S)engine).removeActionListener(this);
			engine = (R) obj;
			((S)engine).addActionListener(this);
			return;
		}
		throw new Exception("Unknown key: "+key);
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand();
		if(s.equals("loaded()")) {rebuild();return;}
	}
	
	
	private void rebuild()
	{
		try
		{
		}
		catch(Exception e)
		{Outside.err(this,"rebuild()",e);}
	}
	
	
	private class TableModel0 extends AbstractTableModel
	{
		public int getColumnCount()
		{return 6;}
		
		public int getRowCount()
		{return list==null?0:list.size();}
		
		public String getColumnName(int y)
		{
			if(y==0) return "JAR";
			if(y==1) return "groupId";
			if(y==2) return "artifactId";
			if(y==3) return "version";
			if(y==4) return "status";
			if(y==5) return "Action";
			return null;
		}
		
		public Class getColumnClass(int y)
		{return String.class;}

		public Object getValueAt(int x, int y)
		{
			if(y==0) return "";
			if(y==1) return "";
			if(y==2) return "";
			if(y==3) return "";
			if(y==4) return "";
			if(y==5) return "";
			return null;
		}
	}
}
