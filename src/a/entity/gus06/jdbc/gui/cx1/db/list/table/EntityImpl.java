package a.entity.gus06.jdbc.gui.cx1.db.list.table;

import a.framework.*;
import javax.swing.JTable;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.ListSelectionModel;

public class EntityImpl extends S1 implements Entity, I, G, P, V {

	public String creationDate() {return "20231111";}


	private Service rendering;
	private Service tableDelay;

	private JTable table;
	private TableModel0 model;
	
	private Map map;
	private List list;

	public EntityImpl() throws Exception
	{
		rendering = Outside.service(this,"gus06.jdbc.gui.cx1.db.list.table.rendering");
		tableDelay = Outside.service(this,"gus06.swing.table.delaysupport.selection");
		
		model = new TableModel0();
		table = new JTable(model);
		table.getTableHeader().setReorderingAllowed(false);
		table.setTableHeader(null);
		table.setShowGrid(false);
		rendering.p(table);
		
		S table_s = (S) tableDelay.t(table);
		table_s.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{selectionChanged();}
		});
		resizeColumns(1,40);
	}
	
	private void resizeColumns(int column, int length)
	{
		table.getColumnModel().getColumn(column).setMinWidth(length);
		table.getColumnModel().getColumn(column).setMaxWidth(length);
	}
	
	
	
	public Object g() throws Exception
	{
		if(table.getSelectionModel().isSelectionEmpty()) return new ArrayList();
		
		int[] rows = table.getSelectedRows();
		List selection = new ArrayList();
		for(int i=0;i<rows.length;i++)
		selection.add(table.getValueAt(rows[i],0));
		
		return selection;
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("selected")) {setSelected((List) obj);return;}
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	public Object i() throws Exception
	{return table;}
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		list = new ArrayList(map.keySet());
		Collections.sort(list);
		model.fireTableDataChanged();
	}
	
	
	private void selectionChanged()
	{send(this,"selectionChanged()");}
	
	
	
	private void setSelected(List selected)
	{
		ListSelectionModel model = table.getSelectionModel();
		model.clearSelection();
		
		if(selected!=null)
		for(int i=0;i<selected.size();i++)
		{
			String s = (String) selected.get(i);
			int index = list.indexOf(s);
			if(index!=-1) model.addSelectionInterval(index, index);
		}
	}
	
	
	private Long countFor(int x)
	{return countFor(nameAt(x));}
	
	private Long countFor(String name)
	{
		if(map==null || name==null) return 0L;
		if(!map.containsKey(name)) return 0L;
		return (Long) map.get(name);
	}
	
	private String nameAt(int x)
	{
		if(list==null) return null;
		return (String) list.get(x);
	}
	
	
	
	private class TableModel0 extends AbstractTableModel
	{
		public int getColumnCount() {return 2;}
		public int getRowCount() {return list!=null ? list.size() : 0;}
		public String getColumnName(int y){return "";}
		public Class getColumnClass(int y){return Object.class;}
		public boolean isCellEditable(int x, int y){return false;}

		public Object getValueAt(int x, int y)
		{
			if(y==0) return nameAt(x);
			if(y==1) return countFor(x);
			return null;
		}
	}
}
