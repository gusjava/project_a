package a.entity.gus06.sys.mailclient1.gui.tab1.tree.renderer;

import a.framework.*;
import javax.swing.JTree;
import javax.swing.JLabel;
import javax.swing.tree.TreeCellRenderer;
import java.awt.Component;
import javax.mail.Folder;
import javax.swing.Icon;
import java.awt.Color;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20201114";}

	public static final Color SELECTION_COLOR = new Color(210,235,235);
	public static final Color OTHER_COLOR = new Color(102,102,102);


	private Service custUI;

	public EntityImpl() throws Exception
	{
		custUI = Outside.service(this,"gus06.swing.tree.cust.ui.expandcollapseicons2");
		
	}
	
	
	public void p(Object obj) throws Exception
	{
		JTree tree = (JTree) obj;
		tree.setCellRenderer(new TreeCellRenderer0());
		custUI.p(tree);
	}
	
	
	
	
	private class TreeCellRenderer0 extends JLabel implements TreeCellRenderer
	{
		public TreeCellRenderer0()
		{
			super();
			setOpaque(true);
		}
		
		public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus)
		{
			R holder = (R) value;
			custLabel(this,holder,selected,expanded);
			return this;
		}
	}
	
	
	
	private void custLabel(JLabel label, R holder, boolean selected, boolean expanded)
	{
		try
		{
			String title = (String) holder.r("title");
			Icon icon = (Icon) holder.r("icon");
			Icon icon_ = (Icon) holder.r("icon_");
			
			Icon icon2 = icon_!=null && expanded ? icon_ : icon;
			Color bg = selected ? SELECTION_COLOR : Color.WHITE;
			Color fg = icon_!=null ? OTHER_COLOR : Color.BLACK;
			
			label.setText(title);
			label.setIcon(icon2);
			label.setBackground(bg);
			label.setForeground(fg);
		}
		catch(Exception e)
		{Outside.err(this,"custLabel(JLabel,R,boolean,boolean)",e);}
	}
}