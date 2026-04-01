package a.entity.gus06.sys.javaprojectviewer1.gui1.package1.list.rendering;

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
	
	
	
	private Icon icon;
	
	public EntityImpl() throws Exception
	{
		icon = (Icon) Outside.resource(this,"icon#JAVA_package");
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
			
			String display = (String) tab[0];
			Map data = (Map) tab[1];

			setText(display(display,data));
			setForeground(foreground());
			setBackground(background(isSelected));
			return this;
		}
	}
	
	private String display(String display, Map data)
	{
		int n = data.size();
		if(n>1) return display+" - "+n;
		return display;
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
