package a.entity.gus06.sys.javaprojectviewer1.gui1.class1.list.rendering;

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

	public String creationDate() {return "20170220";}

	
	public static final Color SELECTION_COLOR = new Color(210,235,235);
	public static final String KEY_CLASSTYPE = "classtype";
	
	
	
	private Icon icon;
	private Icon icon_class;
	private Icon icon_interface;
	private Icon icon_enum;
	
	

	public EntityImpl() throws Exception
	{
		icon = (Icon) Outside.resource(this,"icon#FILE_java");
		icon_class = (Icon) Outside.resource(this,"icon#JAVA_class");
		icon_interface = (Icon) Outside.resource(this,"icon#JAVA_interface");
		icon_enum = (Icon) Outside.resource(this,"icon#JAVA_enum");
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
			setFont(getFont().deriveFont(Font.PLAIN));
		}

		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
		{
			if(value==null) return this;
			Object[] tab = (Object[]) value;
			
			String display = (String) tab[0];
			Map data = (Map) tab[1];

			setText(display);
			setIcon(icon(data));
			setForeground(foreground());
			setBackground(background(isSelected));
			return this;
		}
	}
	
	
	private Icon icon(Map data)
	{
		String type = (String) data.get(KEY_CLASSTYPE);
		if(type.equals("class")) return icon_class;
		if(type.equals("interface")) return icon_interface;
		if(type.equals("enum")) return icon_enum;
		return icon;
	}
	
	
	
	private Color foreground()
	{
		return Color.BLACK;
	}
	
	private Color background(boolean isSelected)
	{
		if(isSelected) 
			return SELECTION_COLOR;
		return Color.WHITE;
	}
}
