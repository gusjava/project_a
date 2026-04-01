package a.entity.gus06.sys.filetool.ext.library1.gui.list.renderer;

import a.framework.*;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;


public class EntityImpl implements Entity, P {

	public String creationDate() {return "20210728";}
	
	public static final Color COLOR_SELECTED = new Color(153,204,255);
	public static final Color COLOR_UNSELECTED = Color.WHITE;
	
	private Service labelDisplay;
	private Service markToColor;
	
	public EntityImpl() throws Exception
	{
		labelDisplay = Outside.service(this,"gus06.swing.label.cust2.display");
		markToColor = Outside.service(this,"gus06.file.findcolor1.marktocolor");
	}
	
	
	public void p(Object obj) throws Exception
	{
		JList list = (JList) obj;
		list.setCellRenderer(new ListRenderer1());
	}
	
	
	private void paintLabel(JLabel label, String display)
	{
		try{labelDisplay.v(display, label);}
		catch(Exception e){Outside.err(this,"paintLabel(JLabel,String)",e);}
	}
	
	private Color fg(String mark)
	{
		try{return (Color) markToColor.t(mark);}
		catch(Exception e){Outside.err(this,"fg(String)",e);}
		return Color.BLACK;
	}
	
	
		
	private Color bg(boolean isSelected)
	{
		return isSelected? COLOR_SELECTED : COLOR_UNSELECTED;
	}
	
	
	private class ListRenderer1 extends JLabel implements ListCellRenderer
	{
		public ListRenderer1()
		{setOpaque(true);}

		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
		{
			String display = (String) value;
			if(display.length()==0)
			{
				setBackground(null);
				setForeground(null);
				setIcon(null);
				setText("");
				return this;
			}
			
			String mark = display.substring(0,1);
			String display1 = display.substring(1);
			
			setBackground(bg(isSelected));
			setForeground(fg(mark));
			paintLabel(this,display1);
			return this;
		}
	}
}