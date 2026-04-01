package a.entity.gus06.sys.xhtml1.ids.gui.detail.renderer;

import a.framework.*;

import java.io.File;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.Icon;


public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220909";}
	
	public static final Color SELECTED_COLOR = new Color(153,204,255);
	
	
	private Service iconProvider;

	public EntityImpl() throws Exception
	{
		iconProvider = Outside.service(this,"gus06.icon.provider");
	}

	private Icon icon(String key)
	{
		try{return (Icon) iconProvider.r(key);}
		catch(Exception e){Outside.err(this,"icon(String)",e);}
		return null;
	}
	
	
	public void p(Object obj) throws Exception
	{
		JList list = (JList) obj;
		list.setCellRenderer(new ListRenderer1());
	}
	
	
	
	
	
	private class ListRenderer1 extends JLabel implements ListCellRenderer
	{
		public ListRenderer1()
		{
			setOpaque(true);
		}

		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
		{
			setBackground(bg(isSelected));
			if(value==null)
			{
				setText("");
				setIcon(null);
			}
			else
			{
				Object[] info = (Object[]) value;
				setText((String) info[1]);
				setIcon(icon("FILE_xhtml"));
			}
			
			return this;
		}
		
		private Color bg(boolean isSelected)
		{return isSelected ? SELECTED_COLOR : Color.WHITE;}
	}
}