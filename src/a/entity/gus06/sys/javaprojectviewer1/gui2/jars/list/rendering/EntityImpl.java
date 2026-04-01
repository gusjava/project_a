package a.entity.gus06.sys.javaprojectviewer1.gui2.jars.list.rendering;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import a.framework.*;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170223";}

	
	public static final Color SELECTION_COLOR = new Color(210,235,235);
	
	
	
	private Icon icon;
	
	public EntityImpl() throws Exception
	{
		icon = (Icon) Outside.resource(this,"icon#FILE_jar");
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
			
			File jar = (File) tab[0];
			Integer group = (Integer) tab[2];

			setText(display(jar,group));
			setForeground(foreground());
			setBackground(background(isSelected));
			return this;
		}
	}
	
	private String display(File jar, Integer group)
	{
		StringBuffer b = new StringBuffer();
		b.append(jar.getName());
		if(group!=null) b.append(" ["+group+"]");
		return b.toString();
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
