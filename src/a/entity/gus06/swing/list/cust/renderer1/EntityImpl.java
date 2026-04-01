package a.entity.gus06.swing.list.cust.renderer1;

import a.framework.*;

import java.awt.Color;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import java.awt.Font;


public class EntityImpl implements Entity, P, V {

	public String creationDate() {return "20140729";}
	
	public static final Color DEFAULT_COLOR = new Color(153,204,255);
	public static final Font DEFAULT_FONT = new Font("Courier",Font.PLAIN,12);
	
	private Service findIcon;
	private Service findColor;
	private Service findFont;
	
	private Icon icon;
	private Color color;
	private Font font;
	
	
	public EntityImpl() throws Exception
	{
		findIcon = Outside.service(this,"gus06.find.icon");
		findColor = Outside.service(this,"gus06.find.color");
		findFont = Outside.service(this,"gus06.find.font");
		
		color = DEFAULT_COLOR;
		font = DEFAULT_FONT;
	}
	
	
	public void p(Object obj) throws Exception
	{
		JList list = (JList) obj;
		if(color==null) color = DEFAULT_COLOR;
		if(font==null) font = DEFAULT_FONT;
		list.setCellRenderer(new ListRenderer1(icon,color,font));
	}
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("icon")) {icon = (Icon) findIcon.t(obj);return;}
		if(key.equals("color")) {color = (Color) findColor.t(obj);return;}
		if(key.equals("font")) {font = (Font) findFont.t(obj);return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	
	private class ListRenderer1 extends JLabel implements ListCellRenderer
	{
		private Icon icon;
		private Color color;
		private Font font;
	
		public ListRenderer1(Icon icon, Color color, Font font)
		{
			setOpaque(true);
			this.icon = icon;
			this.color = color;
			this.font = font;
		}

		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
		{
			setIcon(icon);
			setFont(font);
			setText(value+" ");
			setBackground(bg(isSelected));
			return this;
		}
		
		private Color bg(boolean isSelected)
		{return isSelected?color:Color.WHITE;}
	}
}
