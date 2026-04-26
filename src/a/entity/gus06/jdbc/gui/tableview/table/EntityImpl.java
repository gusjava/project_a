package a.entity.gus06.jdbc.gui.tableview.table;

import a.framework.*;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JTable;
import java.util.List;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import java.sql.ResultSetMetaData;
import java.awt.Dimension;
import javax.swing.table.TableModel;
import javax.swing.ListSelectionModel;
import java.util.Set;
import javax.swing.JLabel;
import javax.swing.table.TableCellRenderer;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.util.Date;
import javax.swing.SwingUtilities;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.Rectangle;
import java.time.LocalDateTime;
import java.math.BigDecimal;

public class EntityImpl extends S1 implements Entity, I, P, E, KeyListener, MouseListener {

	public String creationDate() {return "20190502";}


	private Service selectAllWhere;
	private Service rsToList;
	private Service tooltip;
	private Service adjuster;
	private Service findPk;
	private Service findCx;
	
	
	private JTable table;
	private TableModel0 model;
	
	private String[] colNames;
	private Class[] colClasses;
	private boolean[] colPK;
	private List list;
	
	private Object data;




	public EntityImpl() throws Exception
	{
		selectAllWhere = Outside.service(this,"gus06.jdbc.mysql.perform.select.all.where");
		rsToList = Outside.service(this,"gus06.jdbc.resultset.toobjectarraylist");
		tooltip = Outside.service(this,"gus.x.swing.table.cust.tooltip1");
		adjuster = Outside.service(this,"gus06.swing.table.cust.columnsize.adjuster");
		findPk = Outside.service(this,"gus06.jdbc.mysql.perform.table.findprimarykeys");
		findCx = Outside.service(this,"gus06.jdbc.connection.find");
		
		model = new TableModel0();
		table = new JTable0(model);
		
		TableCellRenderer0 renderer = new TableCellRenderer0();
		
		table.setDefaultRenderer(String.class, renderer);
		table.setDefaultRenderer(Date.class, renderer);
		table.setDefaultRenderer(LocalDateTime.class, renderer);
		table.setDefaultRenderer(Boolean.class, renderer);
		table.setDefaultRenderer(Integer.class, renderer);
		table.setDefaultRenderer(Long.class, renderer);
		table.setDefaultRenderer(Double.class, renderer);
		table.setDefaultRenderer(Float.class, renderer);
		table.setDefaultRenderer(BigDecimal.class, renderer);
		
		table.getTableHeader().setReorderingAllowed(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setCellSelectionEnabled(true);
		
		tooltip.p(table);
		adjuster.p(table);
		
		table.addKeyListener(this);
		table.addMouseListener(this);
	}
	
	
	public Object i() throws Exception
	{return table;}
	
	
	
	public void p(Object obj) throws Exception
	{data = obj;}
	
	
	
	public void e() throws Exception
	{updateGui();}
	
	
	
	private void updateGui() throws Exception
	{
		if(data==null) {resetGui();return;}
		
		Object[] o = (Object[]) data;
		if(o.length!=3 && o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) findCx.t(o[0]);
		String dbName = (String) o[1];
		String tableName = (String) o[2];
		Object where = o.length==4 ? o[3] : null;
		
		if(cx==null) {resetGui();return;}
		
		String path = dbName+"."+tableName;
		Set pkSet = (Set) findPk.t(new Object[]{cx,path});
		
		ResultSet rs = (ResultSet) selectAllWhere.t(new Object[]{cx,path,where});
		ResultSetMetaData rsmd = rs.getMetaData();
		
		int nb = rsmd.getColumnCount();
		String[] colNames1 = new String[nb];
		Class[] colClasses1 = new Class[nb];
		boolean[] colPK1 = new boolean[nb];
		
		for(int i=0;i<nb;i++)
		{
			colNames1[i] = rsmd.getColumnName(i+1);
			colClasses1[i] = Class.forName(rsmd.getColumnClassName(i+1));
			colPK1[i] = pkSet.contains(colNames1[i]);
		}
		List list1 = (List) rsToList.t(rs);
		
		final String[] colNames_ = colNames1;
		final Class[] colClasses_ = colClasses1;
		final boolean[] colPK_ = colPK1;
		final List list_ = list1;
				
		SwingUtilities.invokeLater(new Runnable(){
			public void run()
			{
				colNames = colNames_;
				colClasses = colClasses_;
				colPK = colPK_;
				list = list_;
				
				int row = table.getSelectedRow();
    				int column = table.getSelectedColumn();

				model.fireTableStructureChanged();
				
//				if(table.getRowCount() > row && table.getColumnCount() > column)
//				{
//					table.setRowSelectionInterval(row, row);
//					table.setColumnSelectionInterval(column, column);
//					table.scrollRectToVisible(new Rectangle(table.getCellRect(row, column, true)));
//				}
				modified();
			}
		});
	}
	
	
	
	
	private void resetGui() throws Exception
	{
		SwingUtilities.invokeLater(new Runnable(){
			public void run()
			{
				colNames = new String[0];
				colClasses = new Class[0];
				colPK = new boolean[0];
				list = new ArrayList();
				
				model.fireTableStructureChanged();
				modified();
			}
		});
	}
	
	
	
	private class TableModel0 extends AbstractTableModel
	{
		public int getColumnCount()
		{return colNames==null?0:colNames.length;}
		
		public int getRowCount()
		{return list==null?0:list.size();}
		
		public String getColumnName(int y)
		{
			if(colNames==null) return null;
			if(y>=colNames.length || y<0) return null;
			return colNames[y];
		}
		
		public Class getColumnClass(int y)
		{
			if(colClasses==null) return null;
			if(y>=colClasses.length || y<0) return null;
			return colClasses[y];
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
	
	
	
	private class TableCellRenderer0 extends JLabel implements TableCellRenderer
	{
		private Font font_p;
		private Font font_b;
		private Font font_i;
    	
		public TableCellRenderer0()
		{
			super();
			setOpaque(true);
    			setBackground(Color.WHITE);
    			font_b = getFont().deriveFont(Font.BOLD);
    			font_p = getFont().deriveFont(Font.PLAIN);
    			font_i = getFont().deriveFont(Font.ITALIC);
    		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		{
			setText(""+value);
			
			if(colPK!=null && colPK[column]) setFont(font_b);
			else if(value==null) setFont(font_i);
			else setFont(font_p);
			
			if(isSelected)
				setBackground(Color.LIGHT_GRAY);
			else if(isSelectedArea(table,row,column))
				setBackground(new Color(240,240,240));
			else setBackground(Color.WHITE);
			
			return this;
		}
		
		private boolean isSelectedArea(JTable table, int row, int column)
		{
			for(int selected : table.getSelectedRows()) if(selected==row) return true;
			for(int selected : table.getSelectedColumns()) if(selected==column) return true;
			return false;
		}
	}
	
	
	
	private class JTable0 extends JTable
	{
		public JTable0(TableModel model)
		{super(model);}
		
		public Dimension getPreferredSize()
		{
			int w = 0;
			for(int i=0;i<getColumnModel().getColumnCount();i++)
			w += getColumnModel().getColumn(i).getPreferredWidth();
			
			int h = super.getPreferredSize().height;
			return new Dimension(w,h);
		}
	}
	
	
	
	
	public void keyTyped(KeyEvent e){}
	public void keyReleased(KeyEvent e){}
	public void keyPressed(KeyEvent e)
	{
		int code = e.getKeyCode();
		
		if(e.isControlDown())
		{
			if(code == KeyEvent.VK_C) keyCtrlC();
			if(code == KeyEvent.VK_V) keyCtrlV();
			if(code == KeyEvent.VK_X) keyCtrlX();
			if(code == KeyEvent.VK_DELETE) keyCtrlDelete();
			if(code == KeyEvent.VK_F1) keyCtrlF1();
			if(code == KeyEvent.VK_F2) keyCtrlF2();
			if(code == KeyEvent.VK_F3) keyCtrlF3();
			if(code == KeyEvent.VK_F4) keyCtrlF4();
			if(code == KeyEvent.VK_F5) keyCtrlF5();
                }
		if(e.isAltDown())
		{
			if(code == KeyEvent.VK_C) keyAltC();
			if(code == KeyEvent.VK_V) keyAltV();
			if(code == KeyEvent.VK_X) keyAltX();
			if(code == KeyEvent.VK_DELETE) keyAltDelete();
			if(code == KeyEvent.VK_F1) keyAltF1();
			if(code == KeyEvent.VK_F2) keyAltF2();
			if(code == KeyEvent.VK_F3) keyAltF3();
			if(code == KeyEvent.VK_F4) keyAltF4();
			if(code == KeyEvent.VK_F5) keyAltF5();
                }
		else
		{
			if(code == KeyEvent.VK_DELETE) keyDelete();
			if(code == KeyEvent.VK_F1) keyF1();
			if(code == KeyEvent.VK_F2) keyF2();
			if(code == KeyEvent.VK_F3) keyF3();
			if(code == KeyEvent.VK_F4) keyF4();
			if(code == KeyEvent.VK_F5) keyF5();
		}
		
		table.repaint();
	}
	
	
	public void mouseClicked(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mousePressed(MouseEvent e)
	{
		if(e.getClickCount()==2) doubleClick();
		table.repaint();
	}
	
	
	
	private void modified()
	{send(this,"modified()");}
	
	private void doubleClick()
	{send(this,"doubleClick()");}
	
	
	
	
	private void keyDelete()	{send(this,"keyDelete()");}
	private void keyF1()		{send(this,"keyF1()");}
	private void keyF2()		{send(this,"keyF2()");}
	private void keyF3()		{send(this,"keyF3()");}
	private void keyF4()		{send(this,"keyF4()");}
	private void keyF5()		{send(this,"keyF5()");}
	
	private void keyCtrlC()		{send(this,"keyCtrlC()");}
	private void keyCtrlV()		{send(this,"keyCtrlV()");}
	private void keyCtrlX()		{send(this,"keyCtrlX()");}
	private void keyCtrlDelete()	{send(this,"keyCtrlDelete()");}
	private void keyCtrlF1()	{send(this,"keyCtrlF1()");}
	private void keyCtrlF2()	{send(this,"keyCtrlF2()");}
	private void keyCtrlF3()	{send(this,"keyCtrlF3()");}
	private void keyCtrlF4()	{send(this,"keyCtrlF4()");}
	private void keyCtrlF5()	{send(this,"keyCtrlF5()");}
	
	private void keyAltC()		{send(this,"keyAltC()");}
	private void keyAltV()		{send(this,"keyAltV()");}
	private void keyAltX()		{send(this,"keyAltX()");}
	private void keyAltDelete()	{send(this,"keyAltDelete()");}
	private void keyAltF1()		{send(this,"keyAltF1()");}
	private void keyAltF2()		{send(this,"keyAltF2()");}
	private void keyAltF3()		{send(this,"keyAltF3()");}
	private void keyAltF4()		{send(this,"keyAltF4()");}
	private void keyAltF5()		{send(this,"keyAltF5()");}
	
	
	
}
