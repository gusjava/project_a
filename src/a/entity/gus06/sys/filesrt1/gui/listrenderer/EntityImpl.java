package a.entity.gus06.sys.filesrt1.gui.listrenderer;

import a.framework.*;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.Icon;


public class EntityImpl implements Entity, P {

	public String creationDate() {return "20230106";}
	
	public static final Color COLOR_SELECTED = new Color(153,204,255);
	
	
	private Icon icon;
	
	public EntityImpl() throws Exception
	{
		icon = (Icon) Outside.resource(this,"icon#UTIL_clock");
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
			super();
			setOpaque(true);
		}

		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
		{
			Object[] data = (Object[]) value;
			setBackground(bg(isSelected));
			setIcon(data!=null ? icon : null);
			setText(text(data));
			return this;
		}
		
		private Color bg(boolean isSelected)
		{return isSelected? COLOR_SELECTED : Color.WHITE;}
		
		private String text(Object[] data)
		{
			if(data==null) return "";
			int[] t = (int[]) data[0];
			return format(t[0],2)+":"+format(t[1],2)+":"+format(t[2],2)+" "+format(t[3],3);
		}
		
		private String format(int n, int len)
		{
			String s = ""+n;
			while(s.length()<len) s = "0"+s;
			return s;
		}
	}
}
