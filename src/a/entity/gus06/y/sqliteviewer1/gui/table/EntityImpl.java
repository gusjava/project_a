package a.entity.gus06.y.sqliteviewer1.gui.table;

import a.framework.*;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import java.util.Map;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.awt.Color;
import java.util.List;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20250725";}


	private Service getColumns;
	private Service getContent;
	
	private JPanel panel;
	private JLabel titleLabel;
	private JLabel detailLabel;
	private JScrollPane scroll;
	private JTable table;
	private TableModel0 model;
	
	private G getCx;
	private String tableName;
	
	private List columns;
	private List list;
	

	public EntityImpl() throws Exception
	{
		getColumns = Outside.service(this,"gus06.y.sqlitesql1.retrieve.tablecolumns");
		getContent = Outside.service(this,"gus06.y.sqlitesql1.retrieve.tablecontent");
		
		titleLabel = new JLabel(" ");
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setBorder(BorderFactory.createRaisedBevelBorder());
		
		detailLabel = new JLabel(" ");
		
		model = new TableModel0();
		table = new JTable(model);
		
		scroll = new JScrollPane(table);
		scroll.getViewport().setBackground(Color.WHITE);
		scroll.getViewport().setOpaque(true);
		
		panel = new JPanel(new BorderLayout());
		panel.add(titleLabel, BorderLayout.NORTH);
		panel.add(scroll, BorderLayout.CENTER);
		panel.add(detailLabel, BorderLayout.SOUTH);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null) {reset();return;}
		
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		getCx = (G) o[0];
		tableName = (String) o[1];
		
		columns = (List) getColumns.t(new Object[]{getCx, tableName});
		list = (List) getContent.t(new Object[]{getCx, tableName, columns});
		
		titleLabel.setText(tableName);
		detailLabel.setText(detail());
		model.fireTableStructureChanged();
	}
	
	private void reset()
	{
		getCx = null;
		tableName = null;
		columns = null;
		list = null;
		
		titleLabel.setText(" ");
		detailLabel.setText(" ");
		model.fireTableStructureChanged();
	}
	
	private String detail()
	{
		int rowNb = list==null?0:list.size();
		int colNb = columns==null?0:columns.size();;
		if(colNb==0) return "";
		return "["+rowNb+","+colNb+"]";
	}
	
	private class TableModel0 extends AbstractTableModel
	{
		public int getColumnCount()
		{return columns==null?0:columns.size();}
		
		public int getRowCount()
		{return list==null?0:list.size();}
		
		public String getColumnName(int y)
		{
			if(columns==null) return null;
			if(y>=columns.size() || y<0) return null;
			return (String) columns.get(y);
		}
		
		public Class getColumnClass(int y)
		{
			return Object.class;
		}

		public Object getValueAt(int x, int y)
		{
			if(list==null) return null;
			Object[] row = (Object[]) list.get(x);
			if(row==null) return null;
			if(y>=row.length || y<0) return null;
			return row[y];
		}
	}
}