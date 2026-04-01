package a.entity.gus06.sys.filemanagement1.explore.treerenderer1;

import java.awt.Color;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20191213";}

	public static final Color SELECTION_COLOR = new Color(210,235,235);
	
	public static final String KEY_NAME = "name";
	
	private Service custUI;
	private Service findForeground;
	private Service findIcon;
	
	public EntityImpl() throws Exception
	{
		custUI = Outside.service(this,"gus06.swing.tree.cust.ui.expandcollapseicons2");
		findForeground = Outside.service(this,"gus06.sys.filemanagement1.explore.treerenderer1.findforeground");
		findIcon = Outside.service(this,"gus06.sys.filemanagement1.explore.treerenderer1.findicon");
	}



	public void p(Object obj) throws Exception
	{
		JTree tree = (JTree) obj;
		tree.setCellRenderer(new ExplorerTreeCellRenderer());
		custUI.p(tree);
	}
	
	
	
	
	private Icon findIcon(Map map, boolean expanded)
	{
		try{return (Icon) findIcon.t(new Object[]{map,expanded});}
		catch(Exception e) {Outside.err(this,"findIcon(Map,boolean)",e);}
		return null;
	}
	
	private Color findForeground(Map map)
	{
		try{return (Color) findForeground.t(map);}
		catch(Exception e) {Outside.err(this,"findForeground(Map)",e);}
		return Color.BLACK;
	}
	
	private Color findBackground(boolean selected)
	{
		if(selected) return SELECTION_COLOR;
		return Color.WHITE;
	}
	
	
	
	
	
	
	private class ExplorerTreeCellRenderer extends DefaultTreeCellRenderer
	{
		public Component getTreeCellRendererComponent(JTree tree,Object value,boolean selected,boolean expanded,boolean leaf,int row,boolean hasFocus)
		{
			super.getTreeCellRendererComponent(tree,value,selected,expanded,leaf,row,hasFocus);
			
			if(value==null) return this; 
			if(!(value instanceof Map)) return this;
			
			Map map = (Map) value;
			
			setOpaque(true);
			setIcon(findIcon(map,expanded));
			setForeground(findForeground(map));
			setBackground(findBackground(selected));
			
			String name = (String) map.get(KEY_NAME);
			setText(name);
			 
			return this;
		}
	}
}
