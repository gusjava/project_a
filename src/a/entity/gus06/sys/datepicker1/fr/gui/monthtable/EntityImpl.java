package a.entity.gus06.sys.datepicker1.fr.gui.monthtable;

import java.util.List;
import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

public class EntityImpl implements Entity, P, I {

	public String creationDate() {return "20160616";}

	public static final String[] WEEKDAY = new String[]{"lu","ma","me","je","ve","sa","di"};
	
	private Service monthData;
	private Service render;
	
	private TableModel0 model;
	private JTable table;

	private int[] ym;
	private int[][] data;
	
	
	

	public EntityImpl() throws Exception
	{
		monthData = Outside.service(this,"gus06.time.calendar.buildmonthdata");
		render = Outside.service(this,"gus06.sys.datepicker1.fr.gui.monthtable.render");
		
		model = new TableModel0();
		
		table = new JTable(model);
		table.setCellSelectionEnabled(true);
		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		render.p(table);
	}



	public void p(Object obj) throws Exception
	{
		ym = (int[]) obj;
		data = (int[][]) monthData.t(ym);
		model.fireTableDataChanged();
	}



	public Object i() throws Exception
	{return table;}

	
	
	
	private class TableModel0 extends AbstractTableModel
	{
		public int getColumnCount() {return 7;}
		public int getRowCount() {return 6;}
		
		public Class getColumnClass(int y)
		{return Object.class;}

		public String getColumnName(int y)
		{return WEEKDAY[y];}
		
		public Object getValueAt(int x, int y)
		{
			int year = ym[0];
			int month = ym[1];
			int day = data[x][y];
			
			if(day==-1) return null;
			return new int[]{year,month,day};
		}
	}
}
