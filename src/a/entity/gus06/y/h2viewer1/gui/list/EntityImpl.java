package a.entity.gus06.y.h2viewer1.gui.list;

import a.framework.*;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.util.Map;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.Color;
import java.util.HashMap;

public class EntityImpl extends S1 implements Entity, I, P, G {

	public String creationDate() {return "20250729";}


	private Service retrieveSizeMap;
	private Service tableDelay;
	private Service rendering;
	private Service displayLabel;
	
	private TableModel0 model;
	
        private JLabel numberLabel;
	private JLabel userLabel;
        private JTable table;
	private JScrollPane scroll;
	private JPanel panel;
	
	private G getCx;
	private Map map;
	private List list;
	

	public EntityImpl() throws Exception
	{
		retrieveSizeMap = Outside.service(this,"gus06.y.h2sql1.retrieve.tablesizemap");
		tableDelay = Outside.service(this,"gus06.swing.table.delaysupport.selection");
		rendering = Outside.service(this,"gus06.y.h2viewer1.gui.list.rendering");
		displayLabel = Outside.service(this,"gus06.swing.label.cust2.display");
		
		numberLabel = new JLabel(" ");
		userLabel = new JLabel(" ");
		
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
		
		scroll = new JScrollPane(table);
		scroll.getViewport().setBackground(Color.WHITE);
		scroll.getViewport().setOpaque(true);
		
		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(numberLabel, BorderLayout.CENTER);
		bottomPanel.add(userLabel, BorderLayout.EAST);
		
		panel = new JPanel(new BorderLayout());
		panel.add(scroll, BorderLayout.CENTER);
		panel.add(bottomPanel, BorderLayout.SOUTH);
		
		resizeColumns(1,60);
	}
	
	private void resizeColumns(int column, int length)
	{
		table.getColumnModel().getColumn(column).setMinWidth(length);
		table.getColumnModel().getColumn(column).setMaxWidth(length);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null) {reset();return;}
		
		getCx = (G) obj;
		map = (Map) retrieveSizeMap.t(getCx);
		list = new ArrayList(map.keySet());
		Collections.sort(list);
		
		model.fireTableDataChanged();
		updateNumberLabel();
		updateUserLabel();
	}
	
	
	private void reset() throws Exception
	{
		getCx = null;
		map = null;
		list = null;
		
		model.fireTableDataChanged();
		updateNumberLabel();
		buildUserDisplay();
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
	
	
	private void selectionChanged()
	{send(this,"selectionChanged()");}
	
	
	
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
	
	
	
	private void updateNumberLabel() throws Exception
	{
		numberLabel.setText(buildNumberDisplay());
	}
	
	private String buildNumberDisplay() throws Exception
	{
		if(list==null) return " ";
		return " Number: "+list.size();
	}
	
	private void updateUserLabel() throws Exception
	{
		String userDisplay = buildUserDisplay();
		displayLabel.v(userDisplay, userLabel);
	}
	
	private String buildUserDisplay() throws Exception
	{
		if(getCx==null) return " ";
		String userName = (String) ((R)getCx).r("userName");
		return "USER#"+userName;
	}
}