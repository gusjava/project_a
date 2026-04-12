package a.entity.gus06.sys.treetable1.extmap;

import java.io.File;
import javax.swing.JTree;
import javax.swing.table.AbstractTableModel;
import javax.swing.tree.TreePath;


public abstract class FileTreeTableModel extends AbstractTableModel {

	
	protected JTree tree;
	
	public FileTreeTableModel(JTree tree)
	{this.tree = tree;}
	
	
	
	public int getRowCount() {return tree.getRowCount();}
	public boolean isCellEditable(int row, int column) {return false;}
	
	
	protected Object nodeForRow(int row)
	{
		TreePath treePath = tree.getPathForRow(row);
		return treePath.getLastPathComponent();		 
	}
	
	
	
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		File file = (File)nodeForRow(rowIndex);
		return getValueAt(file,columnIndex);
	}
	
	
	protected abstract Object getValueAt(File file, int columnIndex);

}
