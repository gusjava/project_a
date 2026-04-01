package a.entity.gus06.swing.tree.cust.renderer.obj1;

import a.framework.*;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.TreeCellRenderer;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20161221";}
	
	
	private Service findDisplay;
	private Service repaintLabel;
	private Service custTree;

	public EntityImpl() throws Exception
	{
		findDisplay = Outside.service(this,"gus06.swing.tree.cust.renderer.obj1.finddisplay");
		repaintLabel = Outside.service(this,"gus06.swing.label.cust2.display");
		custTree = Outside.service(this,"gus06.swing.tree.cust.ui.expandcollapseicons2");
	}
	
	

	public void p(Object obj) throws Exception
	{
		JTree tree = (JTree) obj;
		TreeCellRenderer renderer = tree.getCellRenderer();
		tree.setCellRenderer(new TreeCellRenderer0(renderer));
		custTree.p(tree);
	}

	
	private class TreeCellRenderer0 implements TreeCellRenderer
	{
		private TreeCellRenderer renderer;
		public TreeCellRenderer0(TreeCellRenderer renderer)
		{this.renderer = renderer;}
		
		public Component getTreeCellRendererComponent(JTree tree,Object value,boolean sel,boolean expanded,boolean leaf,int row,boolean hasFocus)
		{
			JLabel label = (JLabel) renderer.getTreeCellRendererComponent(tree,value,sel,expanded,leaf,row,hasFocus);
			repaintLabel(label, value);
			return label;
		}
	}
	
	
	
	private void repaintLabel(JLabel label, Object value)
	{
		try
		{
			String display = (String) findDisplay.t(value);
			repaintLabel.v(display,label);
		}
		catch(Exception e)
		{
			label.setIcon(null);
			label.setText("ERR#"+e);
		}
	}
}
