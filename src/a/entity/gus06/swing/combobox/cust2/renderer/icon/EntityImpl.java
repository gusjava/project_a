package a.entity.gus06.swing.combobox.cust2.renderer.icon;

import a.framework.*;

import java.awt.Color;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.ListCellRenderer;
import java.awt.Font;
import javax.swing.JList;


public class EntityImpl implements Entity, V {

	public String creationDate() {return "20200102";}
	
	public static final Color COLOR = new Color(153,204,255);
	
	
	private Service findIcon;
	
	public EntityImpl() throws Exception
	{
		findIcon = Outside.service(this,"gus06.find.icon");
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		Icon icon = (Icon) findIcon.t(key);
		JComboBox combo = (JComboBox) obj;
		
		combo.setRenderer(new ListRenderer1(icon));
		combo.setFont(combo.getFont().deriveFont(Font.PLAIN));
		combo.setBackground(Color.WHITE);
	}
	
	
	private class ListRenderer1 extends JLabel implements ListCellRenderer
	{
		public ListRenderer1(Icon icon)
		{
			setOpaque(true);
			setIcon(icon);
		}

		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
		{
			setText((String) value);
			setBackground(bg(isSelected));
			return this;
		}
		
		private Color bg(boolean isSelected)
		{return isSelected?COLOR:Color.WHITE;}
	}
}
