package a.entity.gus06.swing.table.cust.columnsize.adjuster;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class AdjusterHandler implements TableModelListener, Runnable {

	
	public static final int MAX_WIDTH = 200;
	
	private JTable table;
	
	public AdjusterHandler(JTable table)
	{
		this.table = table;
		table.getModel().addTableModelListener(this);
		adjustColumnPreferredWidths();
	}

	public void tableChanged(TableModelEvent e)
	{SwingUtilities.invokeLater(this);}

	public void run()
	{adjustColumnPreferredWidths();}
	
	
	private void adjustColumnPreferredWidths()
	{
		TableColumnModel columnModel = table.getColumnModel();
		JTableHeader header = table.getTableHeader();
		TableModel model = table.getModel();
		
		synchronized(model)
		{
			for(int col=0;col<model.getColumnCount();col++)
			{
				int maxwidth = 0;	  
				for(int row=0;row<model.getRowCount();row++)
				{
					TableCellRenderer renderer = table.getCellRenderer(row,col); 
					Object value = model.getValueAt(row,col); 
					Component comp = renderer.getTableCellRendererComponent(table,value,false,false,row,col);
					maxwidth = Math.max(comp.getPreferredSize().width,maxwidth);
				}
				
				String name = model.getColumnName(col);
				Component comp = header.getDefaultRenderer().getTableCellRendererComponent(table,name,false,false,-1,col);
				maxwidth = Math.max(comp.getPreferredSize().width,maxwidth);
				
				maxwidth = Math.min(MAX_WIDTH,maxwidth+15);
				columnModel.getColumn(col).setPreferredWidth(maxwidth);
			}
		}
	}
}
