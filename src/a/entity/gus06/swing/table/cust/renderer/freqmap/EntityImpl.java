package a.entity.gus06.swing.table.cust.renderer.freqmap;

import a.framework.*;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import java.awt.Color;

public class EntityImpl implements Entity, P, T, V {

	public String creationDate() {return "20150430";}

	private Color color = Color.GREEN;
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("color")) {color = (Color)obj;return;}
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	public void p(Object obj) throws Exception
	{t(obj);}
	
	
	
	public Object t(Object obj) throws Exception
	{
		JTable table = (JTable) obj;
		FreqTableCellRenderer renderer = new FreqTableCellRenderer(table,color);
		
		table.setDefaultRenderer(Object.class,renderer);
		table.setDefaultRenderer(String.class,renderer);
		table.setDefaultRenderer(Integer.class,renderer);
		
		return renderer;
	}
	
	
	
	private class FreqTableCellRenderer implements TableCellRenderer, V
	{
		private int MAX = 10;
		private int maximum = 10;
		
		private JTable table;
		private Color color = Color.BLUE;
		
		public FreqTableCellRenderer(JTable table, Color color)
		{
			this.table = table;
			this.color = color;
		}
		
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int raw, int column)
		{
			if(column==0) return buildFirst(value);
			if(value instanceof Integer)
				return buildSecond((Integer) value,table);
			return new JLabel();
		}
		
		private Component buildFirst(Object value)
		{
			JLabel label = new JLabel();
			label.setBackground(new Color(230,230,230));
			label.setOpaque(true);
				
			if(value!=null) label.setText(value.toString());
			return label;
		}
		
		private Component buildSecond(Integer value, JTable table)
		{
			int intValue = value.intValue();
			if(intValue >= maximum)
			{
				maximum += MAX;
				table.repaint();
			}
			
			JProgressBar progress = new JProgressBar(0,maximum);
			progress.setBorderPainted(false);
			progress.setBackground(Color.WHITE);
			progress.setForeground(color);
			
			progress.setValue(intValue);
			return progress;
		}
		
		public void v(String key, Object obj) throws Exception
		{
			if(key.equals("color"))
			{
				color = (Color) obj;
				table.repaint();
				return;
			}
			throw new Exception("Unknown key: "+key);
		}
	}
}
