package a.entity.gus06.convert.colortojtooltip;

import a.framework.*;
import javax.swing.JToolTip;
import java.awt.Color;
import java.awt.Dimension;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220611";}
	
	public Object t(Object obj) throws Exception
	{
		return new JToolTipColor((Color) obj);
	}
	
	
	private class JToolTipColor extends JToolTip
	{
		private Color color;
		
		public JToolTipColor(Color color)
		{
			super();
			this.color = color;
			setBackground(color);
		}
		
		public int getWidth()
		{return 15;}
		
		public int getHeight()
		{return 15;}
		
		public Dimension getPreferredSize()
		{return new Dimension(getWidth(), getHeight());}
		
		public Dimension getSize()
		{return new Dimension(getWidth(), getHeight());}
	}
}