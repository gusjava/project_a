package a.entity.gus06.sys.javaprojectviewer1.gui1.import1.list.rendering;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170222";}

	
	public static final Color SELECTION_COLOR = new Color(210,235,235);
	
	public static final Color COLOR_PROJECT = Color.BLACK;
	public static final Color COLOR_JRE = new Color(0,153,204);
	public static final Color COLOR_UNKNOWN = new Color(255,153,0);
	public static final Color COLOR_ERROR = Color.RED;
	
	public static final String TYPE_PROJECT = "$";
	public static final String TYPE_JRE = "*";
	public static final String TYPE_UNKNOWN = "?";
	public static final String TYPE_ERROR = "#";

	
	
	
	private Icon icon;

	public EntityImpl() throws Exception
	{
		icon = (Icon) Outside.resource(this,"icon#JAVA_import");
	}



	public void p(Object obj) throws Exception
	{
		JList list = (JList) obj;
		list.setCellRenderer(new ListRenderer0());
	}
	
	
	
	
	private class ListRenderer0 extends JLabel implements ListCellRenderer
	{   
		public ListRenderer0()
		{
			super();
			setOpaque(true);
			setBackground(Color.WHITE);
			setIcon(icon);
			setFont(getFont().deriveFont(Font.PLAIN));
		}

		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
		{
			if(value==null) return this;
			Object[] tab = (Object[]) value;
			
			String info = (String) tab[0];
			Map data = (Map) tab[1];
			
			int size = data.size();
			
			int len = info.length();
			String type = info.substring(len-1,len);
			String display = info.substring(0,len-1);

			setText(display(display,size));
			setForeground(foreground(type));
			setBackground(background(isSelected));
			
			return this;
		}
	}
	
	
	private String display(String display, int size)
	{
		StringBuffer b = new StringBuffer();
		b.append(display);
		if(size!=1) b.append(" - "+size);
		return b.toString();
	}
	
	
	private Color foreground(String type)
	{
		if(type.equals(TYPE_PROJECT)) return COLOR_PROJECT;
		if(type.equals(TYPE_JRE)) return COLOR_JRE;
		if(type.equals(TYPE_UNKNOWN)) return COLOR_UNKNOWN;
		if(type.equals(TYPE_ERROR)) return COLOR_ERROR;
		return Color.GREEN;
	}
	
	private Color background(boolean isSelected)
	{
		if(isSelected) return SELECTION_COLOR;
		return Color.WHITE;
	}
}
