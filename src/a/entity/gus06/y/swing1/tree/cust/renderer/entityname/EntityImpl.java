package a.entity.gus06.y.swing1.tree.cust.renderer.entityname;

import java.awt.Color;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.TreeCellRenderer;
import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20251121";}

	public static final Color COLOR_SELECT = new Color(244, 244, 244);
	public static final Color COLOR_UNSELECT = Color.WHITE;

	private Icon icon;

	public EntityImpl() throws Exception
	{
		icon = (Icon) Outside.resource(this, "icon#ELEMENT_entity");
	}

	public void p(Object obj) throws Exception
	{
		JTree tree = (JTree) obj;
		tree.setCellRenderer(new TreeCellRenderer0());
	}

	private class TreeCellRenderer0 extends JLabel implements TreeCellRenderer
	{
		public TreeCellRenderer0()
		{
			super();
			setOpaque(true);
		}

		public Component getTreeCellRendererComponent(JTree tree, Object value, boolean isSelected, boolean expanded,
				boolean leaf, int row, boolean hasFocus) {
			setIcon(icon);
			setText((String) value);

			setBackground(getBackground(isSelected));
			return this;
		}

		public Color getBackground(boolean isSelected)
		{return isSelected ? COLOR_SELECT : COLOR_UNSELECT;}
	}
}
