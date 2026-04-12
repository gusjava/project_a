package a.entity.gus06.file.excel.jxl.sheetjtable.v2;

import javax.swing.table.AbstractTableModel;

import jxl.Cell;
import jxl.Sheet;

public class SheetTableModel extends AbstractTableModel
{
	private Sheet sheet;
	
	public SheetTableModel(Sheet sheet)
	{this.sheet = sheet;}
	
    public int getColumnCount() {return sheet.getColumns();}
    public int getRowCount() {return sheet.getRows();}
    
    public boolean isCellEditable(int x, int y){return false;}
    public Class getColumnClass(int y){return Cell.class;}
    
    public Object getValueAt(int x, int y)
    {
        Cell[] row = sheet.getRow(x);
        if(y>=row.length) return null;
        return row[y];
    } 
}


