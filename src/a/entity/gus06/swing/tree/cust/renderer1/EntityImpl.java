package a.entity.gus06.swing.tree.cust.renderer1;

import a.framework.*;

import java.awt.Color;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;


public class EntityImpl implements Entity, P, V {

	public String creationDate() {return "20140917";}
	
	public static final Color DEFAULT_COLOR = new Color(153,204,255);
	
	private Service findIcon;
	private Service findColor;
	
	private Icon icon;
	private Color color;
	
	
	public EntityImpl() throws Exception
	{
		findIcon = Outside.service(this,"gus06.find.icon");
		findColor = Outside.service(this,"gus06.find.color");
		color = DEFAULT_COLOR;
	}
	
	
	public void p(Object obj) throws Exception
	{
		JTree tree = (JTree) obj;
		if(color==null) color = DEFAULT_COLOR;
		tree.setCellRenderer(new TreeRenderer1(icon,color));
	}
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("icon")) {icon = (Icon) findIcon.t(obj);return;}
		if(key.equals("color")) {color = (Color) findColor.t(obj);return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	
	private class TreeRenderer1 extends DefaultTreeCellRenderer
	{
		private Icon icon;
		private Color color;
	
		public TreeRenderer1(Icon icon, Color color)
		{
			setOpaque(true);
			this.icon = icon;
			this.color = color;
		}
		
		public Component getTreeCellRendererComponent(JTree tree,Object value,boolean sel,boolean expanded,boolean leaf,int row,boolean hasFocus)
		{
			super.getTreeCellRendererComponent(tree,value,sel,expanded,leaf,row,hasFocus);
			if(value==null) return this;
			
			setIcon(icon);
			setText(value.toString());
			setBackground(bg(sel)); 
			return this;
		}
		
		private Color bg(boolean isSelected)
		{return isSelected?color:Color.WHITE;}
	}
}
