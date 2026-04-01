package a.entity.gus06.swing.tree.cust.renderer.display1;

import a.framework.*;

import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.TreeCellRenderer;



public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150425";}
	
	
	private Service iconProvider;

	public EntityImpl() throws Exception
	{iconProvider = Outside.service(this,"gus06.icon.provider");}


	private Icon icon(String key)
	{
		try{return (Icon) iconProvider.r(key);}
		catch(Exception e){Outside.err(this,"icon(String)",e);}
		return null;
	}
	
	private Icon icon(boolean expanded)
	{return expanded?icon("dir_"):icon("dir");}
	
	

	public void p(Object obj) throws Exception
	{
		JTree tree = (JTree) obj;
		TreeCellRenderer renderer = tree.getCellRenderer();
		tree.setCellRenderer(new TreeCellRenderer0(renderer));
	}

	
	
	
	private void repaintLabel(JLabel label, String title, boolean expanded)
	{
		String[] n = title.split("#",2);
		if(n.length>1)
		{
			label.setIcon(icon(n[0]));
			label.setText(n[1]);
		}
		else
		{
			label.setIcon(icon(expanded));
			label.setText(n[0]);
		}
	}
	
	
	
	
	private class TreeCellRenderer0 implements TreeCellRenderer
	{
		private TreeCellRenderer renderer;
		public TreeCellRenderer0(TreeCellRenderer renderer)
		{this.renderer = renderer;}
		
		public Component getTreeCellRendererComponent(JTree tree,Object value,boolean sel,boolean expanded,boolean leaf,int row,boolean hasFocus)
		{
			JLabel label = (JLabel) renderer.getTreeCellRendererComponent(tree,value,sel,expanded,leaf,row,hasFocus);
			String[] m = value.toString().split("=",2);
			String title = m[0];
			
			repaintLabel(label,title,expanded);
			return label;
		}
	}
}

