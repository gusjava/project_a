package a.entity.gus06.sys.xhtml1.ids.gui.table;

import a.framework.*;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.awt.BorderLayout;
import java.util.HashMap;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;

public class EntityImpl extends S1 implements Entity, G, P, I {

	public String creationDate() {return "20220908";}


	private Service findAll;
	private Service fieldHolder;
	private Service linkerTableField;
	private Service listFilter;

	
	private JPanel panel;
    
	private TableModel0 model;
	private JTable table;
	private JScrollPane scroll;
	private JLabel labelNumber;
	
	private Map data = new HashMap();
	private List ids = new ArrayList();
	private List ids1 = new ArrayList();
	

	public EntityImpl() throws Exception
	{
		findAll = Outside.service(this,"gus06.sys.xhtml1.ids.findall.asmap");
		fieldHolder = Outside.service(this,"*gus06.data.editor.string.textfield.editor1");
		linkerTableField = Outside.service(this,"gus.x.swing.table.textfield.linker");
		listFilter = Outside.service(this,"gus06.list.filter.rule.all");
		
		fieldHolder.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{refresh();}
		});
		
		model = new TableModel0();
		table = new JTable(model);
		table.setShowGrid(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e)
			{selectionChanged();}
		});
		linkerTableField.p(new Object[]{table, fieldHolder.i()});
		
		scroll = new JScrollPane(table);
		scroll.getViewport().setBackground(Color.WHITE);
    	
		labelNumber = new JLabel(" ");
    	
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) fieldHolder.i(), BorderLayout.NORTH);
		panel.add(scroll, BorderLayout.CENTER);
		panel.add(labelNumber, BorderLayout.SOUTH);
		
		resizeColumns(1,50);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		data = (Map) findAll.t(obj);
		ids = data!=null ? new ArrayList(data.keySet()) : new ArrayList();
		Collections.sort(ids);
		refresh();
	}
	
	public Object g() throws Exception
	{
		if(table.getSelectionModel().isSelectionEmpty()) return null;
		
		int row = table.getSelectedRow();
		String id = (String) table.getValueAt(row, 0);
		return data.get(id);
	}
	
	
	private class TableModel0 extends AbstractTableModel
	{
		public int getColumnCount() {return 2;}
		public int getRowCount() {return ids1.size();}
		public Class getColumnClass(int y) {return String.class;}
		
		public String getColumnName(int y)
		{
			if(y==0) return "id";
			if(y==1) return "nb";
			return null;
		}

		public Object getValueAt(int x, int y)
		{
			String id = (String) ids1.get(x);
			if(y==0) return id;
			
			List idInfo = (List) data.get(id);
			return ""+idInfo.size();
		}
	}
	
	private void resizeColumns(int column, int length)
	{
		table.getColumnModel().getColumn(column).setMinWidth(length);
		table.getColumnModel().getColumn(column).setMaxWidth(length);
	}
	
	
	private void selectionChanged()
	{send(this,"selectionChanged()");}
	
	
	
	
	private void refresh()
	{
		try
		{
			ids1 = (List) listFilter.t(new Object[]{ids, query()});
			labelNumber.setText(resultNumber());
			model.fireTableDataChanged();
		}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}
	
	
	
	private String query() throws Exception
	{return (String) fieldHolder.g();}
	
	
	private String resultNumber()
	{
		int size = ids.size();
		int size1 = ids1.size();
		return size==size1 ? " "+size : " "+size1+"/"+size;
	}
}