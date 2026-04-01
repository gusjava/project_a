package a.entity.gus06.convert.icontojtooltip;

import a.framework.*;
import javax.swing.JToolTip;
import javax.swing.Icon;
import java.awt.Graphics;
import java.awt.Dimension;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220611";}
	
	public Object t(Object obj) throws Exception
	{
		return new JToolTipIcon((Icon) obj);
	}
	
	
	private class JToolTipIcon extends JToolTip
	{
		private Icon icon;
		
		public JToolTipIcon(Icon icon)
		{
			super();
			this.icon = icon;
		}
		
		protected void paintComponent(Graphics g)
		{icon.paintIcon(this, g, 0, 0);}
		
		public int getWidth()
		{return icon.getIconWidth();}
		
		public int getHeight()
		{return icon.getIconHeight();}
		
		public Dimension getPreferredSize()
		{return new Dimension(getWidth(), getHeight());}
		
		public Dimension getSize()
		{return new Dimension(getWidth(), getHeight());}
	}
}