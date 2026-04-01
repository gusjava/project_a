package a.entity.gus06.sys.treetable1.extmap.renderer;

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
		table.setDefaultRenderer(Integer.class,renderer);
	}
	
	
	private String format(Long l)
	{
		try{return formatLong.t(""+l)+" ";}
		catch(Exception e) {Outside.err(this,"format(Long)",e);}
		return l+" ";
	}
	
	
	private class TableCellRenderer1 extends JLabel implements TableCellRenderer
	{
		private Color colorSelected;
		private Color colorUnselected;
		
		public TableCellRenderer1()
		{
			super();
			setOpaque(true);
			setHorizontalAlignment(JLabel.RIGHT);
			
			colorSelected = (new JLabel()).getBackground();
			colorUnselected = Color.WHITE;
		}
		
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int raw, int column)
		{
			setBackground(findBackground(isSelected));
			setText(findDisplay(value));
			return this;
		}
		
		
		private Color findBackground(boolean isSelected)
		{return isSelected ? colorSelected : colorUnselected;}
		
		
		private String findDisplay(Object value)
		{
			if(value==null) return " ";
			int n = ((Integer) value).intValue();
			return n==0 ? " " : n+" ";
		}
	}
}
