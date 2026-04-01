package a.entity.gus06.sys.datepicker1.fr.gui.monthtable.render;

import a.framework.*;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160616";}

	private Color color_today;
	private Color color_weekend;
	private Color color_selected;
	private Color color_default;

	private Border border_1;
	private Border border_2;


	private Service isToday;
	private Service colorManager;


	public EntityImpl() throws Exception
	{
		isToday = Outside.service(this,"gus06.time.date.int3.is.today");
		colorManager = Outside.service(this,"gus06.sys.datepicker1.fr.color.manager");
		
		color_weekend = (Color) colorManager.r("weekend");
		color_today = (Color) colorManager.r("today");
		color_selected = (Color) colorManager.r("selected");
		color_default = (Color) colorManager.r("default");
		
		border_1 = BorderFactory.createEmptyBorder();
		border_2 = BorderFactory.createLineBorder(color_selected);
	}
	
	
	private boolean isToday(int y, int m, int d)
	{
		if(d==-1) return false;
		try{return isToday.f(new int[]{y,m,d});}
		catch(Exception e){Outside.err(this,"isToday(int,int,int)",e);}
		return false;
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		JTable table = (JTable) obj;
		table.setDefaultRenderer(Object.class,new TableCellRenderer0());
	}
	
	
	private class TableCellRenderer0 extends JLabel implements TableCellRenderer
	{
		private Font font_p;
		private Font font_b;
		
		public TableCellRenderer0()
		{
			super();
			setOpaque(true);
			setBackground(Color.WHITE);
			setHorizontalAlignment(JLabel.RIGHT);

			font_p = getFont().deriveFont(Font.PLAIN).deriveFont((float)12);
			font_b = getFont().deriveFont(Font.BOLD).deriveFont((float)12);
		}
		
		public Component getTableCellRendererComponent(
				JTable table, Object value, boolean isSelected, 
				boolean hasFocus, int row, int column) {
			
			int[] n = (int[]) value;
			if(n==null)
			{
				setText(" ");
				setFont(font_p);
				setBorder(border_1);
				setForeground(Color.BLACK);
				return this;
			}
			
			int y = n[0];
			int m = n[1];
			int d = n[2];
			
			boolean isToday = isToday(y,m,d);
			boolean isWeekend = column==5 || column==6;
			
			String text = d<=0 ? " " : d+" ";
			Font font = isSelected ? font_b : font_p;
			Border border = isSelected ? border_2 : border_1;
			
			Color foreground = 
				isSelected ? color_selected : 
				isToday ? color_today : 
				isWeekend ? color_weekend :
				Color.BLACK;
			
			setText(text);
			setFont(font);
			setBorder(border);
			setForeground(foreground);
			
			return this;
		}
	}
}