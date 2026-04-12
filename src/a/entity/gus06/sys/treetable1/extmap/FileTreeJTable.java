package a.entity.gus06.sys.treetable1.extmap;

import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;


public class FileTreeJTable extends JTable {

	private JTree tree;
	private FileTreeTableModel model;
	

	public FileTreeJTable(FileTreeTableModel model, JTree tree)
	{
		super();
		this.tree = tree;
		this.model = model;
		
		setModel(model);
		
		setEnabled(false);
		setShowGrid(false);
		setIntercellSpacing(new Dimension(0, 0));
		setBackground(tree.getBackground());
		
		tree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener(){
			public void valueChanged(TreeSelectionEvent e){
				updateRowSelection();
			}});
		
		tree.addTreeExpansionListener(new TreeExpansionListener() {
			public void treeExpanded(TreeExpansionEvent e) {rebuildTable();}
			public void treeCollapsed(TreeExpansionEvent e) {rebuildTable();}
		});
		
		tree.addPropertyChangeListener(JTree.TREE_MODEL_PROPERTY,new PropertyChangeListener(){
			public void propertyChange(PropertyChangeEvent evt) {rebuildTable();}
		});
	}
	
	
	
	private void rebuildTable()
	{
		model.fireTableDataChanged();
		updateRowSelection();
	}
	
	
	private void updateRowSelection()
	{
		if(tree.isSelectionEmpty())
		{getSelectionModel().clearSelection();return;}
		
		TreePath path = tree.getSelectionPath();
		int row = tree.getRowForPath(path);
		getSelectionModel().setSelectionInterval(row,row);
	}

}
