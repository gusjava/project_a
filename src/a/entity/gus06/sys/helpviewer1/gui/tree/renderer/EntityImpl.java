package a.entity.gus06.sys.helpviewer1.gui.tree.renderer;

import a.framework.*;

import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.TreeCellRenderer;
import java.util.Map;



public class EntityImpl implements Entity, P {

	public String creationDate() {return "20161022";}
	
	public static final String KEY_NAME = "name";
	public static final String ICON_CLOSE = "HELP_section";
	public static final String ICON_OPEN = "HELP_section_";
	public static final String ICON_LEAF = "HELP_page";
	
	
	private Service iconProvider;

	public EntityImpl() throws Exception
	{iconProvider = Outside.service(this,"gus06.icon.provider");}


	private Icon icon(String key)
	{
		try{return (Icon) iconProvider.r(key);}
		catch(Exception e){Outside.err(this,"icon(String)",e);}
		return null;
	}
	

	public void p(Object obj) throws Exception
	{
		JTree tree = (JTree) obj;
		TreeCellRenderer renderer = tree.getCellRenderer();
		tree.setCellRenderer(new TreeCellRenderer0(renderer));
	}

	
	
	
	private void repaintLabel(JLabel label, String title, boolean expanded, boolean leaf)
	{
		String[] n = title.split("#",2);
		if(n.length>1)
		{
			label.setIcon(icon(n[0]));
			label.setText(n[1]);
		}
		else if(!leaf)
		{
			label.setIcon(icon(expanded?ICON_OPEN:ICON_CLOSE));
			label.setText(n[0]);
		}
		else
		{
			label.setIcon(icon(ICON_LEAF));
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
			
			Map map = (Map) value;
			String name = (String) map.get(KEY_NAME);
			
			repaintLabel(label,name,expanded,leaf);
			return label;
		}
	}
}
