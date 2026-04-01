package a.entity.gus06.swing.border.build.ovalborder;

import a.framework.*;
import java.awt.*;
import javax.swing.border.Border;

public class EntityImpl implements Entity, G, T {

	public String creationDate() {return "20140913";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public Object g() throws Exception
	{return new OvalBorder();}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof int[])
		{
			int[] n = (int[]) obj;
			return new OvalBorder(n[0],n[1]);
		}
		if(obj instanceof Object[])
		{
			Object[] o = (Object[]) obj;
		
			Integer w = (Integer) o[0];
			Integer h = o.length>1 ? (Integer) o[1] : w;
			Color color1 = o.length>2 ? (Color) o[2] : Color.BLACK;
			Color color2 = o.length>3 ? (Color) o[3] : color1;
			
			return new OvalBorder(w,h,color1,color2);
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	
	public class OvalBorder implements Border
	{
		protected int m_w=12;
		protected int m_h=12;
		protected Color m_topColor = Color.white;
		protected Color m_bottomColor = Color.gray;

		public OvalBorder()
		{
			m_w=6;
			m_h=6;
		}

		public OvalBorder(int w, int h)
		{
			m_w=w;
			m_h=h;
		}

		public OvalBorder(int w, int h, Color topColor, Color bottomColor)
		{
			m_w=w;
			m_h=h;
			m_topColor = topColor;
			m_bottomColor = bottomColor;
		}

		public Insets getBorderInsets(Component c)
		{return new Insets(m_h, m_w, m_h, m_w);}
		
		public boolean isBorderOpaque()
		{return true;}
		
		public void paintBorder(Component c, Graphics g, int x, int y, int w, int h)
		{
			Graphics2D g2 = (Graphics2D) g;
			w--;
			h--;
			
			g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,	RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,		RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,		RenderingHints.VALUE_COLOR_RENDER_QUALITY);
			g2.setRenderingHint(RenderingHints.KEY_DITHERING,		RenderingHints.VALUE_DITHER_ENABLE);
			g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,	RenderingHints.VALUE_FRACTIONALMETRICS_ON);
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,		RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2.setRenderingHint(RenderingHints.KEY_RENDERING,		RenderingHints.VALUE_RENDER_QUALITY);
			g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,		RenderingHints.VALUE_STROKE_PURE);
			
			g2.setColor(m_topColor);
			g2.drawLine(x, y+h-m_h, x, y+m_h);
			g2.drawArc(x, y, 2*m_w, 2*m_h, 180, -90);
			g2.drawLine(x+m_w, y, x+w-m_w, y);
			g2.drawArc(x+w-2*m_w, y, 2*m_w, 2*m_h, 90, -90);
			
			g2.setColor(m_bottomColor);
			g2.drawLine(x+w, y+m_h, x+w, y+h-m_h);
			g2.drawArc(x+w-2*m_w, y+h-2*m_h, 2*m_w, 2*m_h, 0, -90);
			g2.drawLine(x+m_w, y+h, x+w-m_w, y+h);
			g2.drawArc(x, y+h-2*m_h, 2*m_w, 2*m_h, -90, -90);
		}
	}
}
