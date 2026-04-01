package a.entity.gus06.sys.filetool.ext.idea1.holder.renderer;

import a.framework.*;

import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.TreeCellRenderer;
import java.awt.Color;
import java.io.File;

public class EntityImpl implements Entity, P, V {

	public String creationDate() {return "20200827";}
	
	public static final Color COLOR_EDITMODE = Color.GRAY;
	
	
	private Service iconBuilder;
	private File iconDir;

	public EntityImpl() throws Exception
	{
		iconBuilder = Outside.service(this,"gus06.icon.builder2");
	}


	private Icon icon(String key)
	{
		try{return (Icon) iconBuilder.t(new Object[]{iconDir,key});}
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
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("iconDir"))
		{
			iconDir = (File) obj;
			return;
		}
		throw new Exception("Unknown key: "+key);
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
	
	private boolean isEditMode(JTree tree)
	{
		try{return ((F) tree).f("editMode");}
		catch(Exception e)
		{Outside.err(this,"isEditMode(JTree)",e);}
		return false;
	}
	
	
	
	
	private class TreeCellRenderer0 implements TreeCellRenderer
	{
		private TreeCellRenderer renderer;
		public TreeCellRenderer0(TreeCellRenderer renderer)
		{this.renderer = renderer;}
		
		public Component getTreeCellRendererComponent(JTree tree,Object value,boolean sel,boolean expanded,boolean leaf,int row,boolean hasFocus)
		{
			JLabel label = (JLabel) renderer.getTreeCellRendererComponent(tree,value,sel,expanded,leaf,row,hasFocus);
			String title = value.toString();
			
			repaintLabel(label,title,expanded);
			label.setForeground(isEditMode(tree) ? COLOR_EDITMODE : Color.BLACK);
			return label;
		}
	}
}