package a.entity.gus06.font.availablefonts.buildcombo;

import a.framework.*;
import java.awt.Font;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.ListCellRenderer;
import java.awt.Component;
import java.awt.Color;
import javax.swing.JList;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20160501";}


	private Service availableFonts;

	public EntityImpl() throws Exception
	{
		availableFonts = Outside.service(this,"gus06.font.availablefonts.list.p12");
	}
	
	
	
	public Object i() throws Exception
	{
		List fonts = (List) availableFonts.g();
		JComboBox combo = new JComboBox();
		for(int i=0;i<fonts.size();i++)
		{
			Font font = (Font) fonts.get(i);
			combo.addItem(font);
		}
		combo.setRenderer(new ListCellRenderer0());
		return combo;
	}
	
	
	
	
	private class ListCellRenderer0 extends JLabel implements ListCellRenderer
	{
		public ListCellRenderer0()
		{
			super();
			setOpaque(true);
		}
		
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean hasFocus)
		{
			Font font = (Font) value;
			if(font==null)
			{
				setText("null");
				return this;
			}
			setFont(font);
			setBackground(isSelected?Color.LIGHT_GRAY:Color.WHITE);
			setText(font.getFontName());
			return this;
		}
	}
}
