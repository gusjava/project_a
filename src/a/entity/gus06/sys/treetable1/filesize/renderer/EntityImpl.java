package a.entity.gus06.sys.treetable1.filesize.renderer;

import a.framework.*;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import java.awt.Color;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20161117";}


	private Service formatLong;

	public EntityImpl() throws Exception
	{
		formatLong = Outside.service(this,"gus06.string.transform.format.number.by3");
	}
	
	
	public void p(Object obj) throws Exception
	{
		JTable table = (JTable) obj;
		TableCellRenderer1 renderer = new TableCellRenderer1();
		table.setDefaultRenderer(Long.class,renderer);
	}
	
	
	private String format(Long l)
	{
		try{return formatLong.t(""+l)+" ";}
		catch(Exception e) {Outside.err(this,"format(Long)",e);}
		return l+" ";
	}
	
	
	private class TableCellRenderer1 implements TableCellRenderer
	{
		private int maximum = 0;
		private JLabel label;
		private JProgressBar progress;
		
		private Color colorProgress;
		private Color colorSelected;
		private Color colorUnselected;
		
		public TableCellRenderer1()
		{
			colorProgress = new Color(102,153,255);
			colorSelected = (new JLabel()).getBackground();
			colorUnselected = Color.WHITE;
			
			label = new JLabel();
			label.setOpaque(true);
			label.setHorizontalAlignment(JLabel.RIGHT);
			
			progress = new JProgressBar();
			progress.setMinimum(0);
			progress.setBorderPainted(false);
			progress.setForeground(colorProgress);
		}
		
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int raw, int column)
		{
			if(value==null) return new JLabel();
			Long l = (Long) value;
			
			if(column==0) return build1(l,isSelected);
			if(column==1) return build2(l,isSelected,table);
			return new JLabel();
		}
		
		private Component build1(Long l, boolean isSelected)
		{
			label.setText(format(l));
			label.setBackground(findBackground(isSelected));
			return label;
		}
		
		private Component build2(Long l, boolean isSelected, JTable table)
		{
			int intValue = l.intValue();
			int maxValue = (int) (intValue * 1.2);
			
			if(maximum < maxValue)
			{
				maximum = maxValue;
				table.repaint();
			}
			
			progress.setMaximum(maximum);
			progress.setBackground(findBackground(isSelected));
			progress.setValue(intValue);
			return progress;
		}
		
		private Color findBackground(boolean isSelected)
		{return isSelected ? colorSelected : colorUnselected;}
	}
}
