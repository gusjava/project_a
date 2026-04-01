package a.entity.gus06.sys.git1.filehistory.gui.commitlist.renderer;

import a.framework.*;
import javax.swing.JTable;
import java.awt.Color;
import java.util.Date;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

public class EntityImpl extends S1 implements Entity, P {

	public String creationDate() {return "20201201";}
	
	public static final Color COLOR_SELECTION = new Color(153,204,255);
	public static final Border BORDER_GAP = BorderFactory.createMatteBorder(1,0,0,0,Color.GRAY);

	private Service formatTime;
	
	private Icon iconE;
	private Icon icon0;
	private Icon iconN;
	private Icon iconD;
	private Icon iconA;
	private Icon iconDA;
	private Icon iconR;
	private Icon iconDR;


	public EntityImpl() throws Exception
	{
		formatTime = Outside.service(this,"gus06.time.date.format.datetime.fr.format1");
		
		iconE = (Icon) Outside.resource(this,"icon#UTIL_error");
		icon0 = (Icon) Outside.resource(this,"icon#GIT_state_0");
		iconN = (Icon) Outside.resource(this,"icon#GIT_state_n");
		iconD = (Icon) Outside.resource(this,"icon#GIT_state_d");
		iconA = (Icon) Outside.resource(this,"icon#GIT_state_a");
		iconDA = (Icon) Outside.resource(this,"icon#GIT_state_da");
		iconR = (Icon) Outside.resource(this,"icon#GIT_state_r");
		iconDR = (Icon) Outside.resource(this,"icon#GIT_state_dr");
	}
	
	
	
	
	public void p(Object obj) throws Exception
	{
		JTable table = (JTable) obj;
		
		TableCellRenderer1 renderer = new TableCellRenderer1();
		table.setDefaultRenderer(Integer.class,renderer);
		table.setDefaultRenderer(String.class,renderer);
		table.setDefaultRenderer(Date.class,renderer);
	}
	
	
	
	
	private class TableCellRenderer1 extends JLabel implements TableCellRenderer
	{
		public TableCellRenderer1()
		{
			super();
			setOpaque(true);
			setBackground(Color.WHITE);
		}
		
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		{
			if(column==0)
			{
				setIcon(null);
				setText(textForIndex((Integer) value));
			}
			else if(column==1)
			{
				setIcon(iconForState((String) value));
				setText(textForState((String) value));
			}
			else if(column==2)
			{
				setIcon(null);
				setText(formatTime((Date) value));
			}
			else
			{
				setIcon(null);
				setText(""+value);
			}
			setBackground(background(isSelected));
			setBorder(border(table, row));
			return this;
		}
	}
	
	
		
	private Color background(boolean isSelected)
	{return isSelected ? COLOR_SELECTION : Color.WHITE;}
	
	
	private Border border(JTable table, int row)
	{
		if(row<=0) return null;
		Integer index0 = (Integer) table.getValueAt(row-1,0);
		Integer index1 = (Integer) table.getValueAt(row,0);
		
		if(index0==null || index1==null) return null;
		return Math.abs(index1-index0)>1 ? BORDER_GAP : null;
	}
	
	
	
	private String formatTime(Date date)
	{
		if(date==null) return "current";
		try{return (String) formatTime.t(date);}
		catch(Exception e){Outside.err(this,"formatTime()",e);}
		return "###";
	}
	
	
	private Icon iconForState(String state)
	{
		if(state==null) return null;
		if(state.equals("")) return null;
		if(state.equals("E")) return iconE;
		if(state.equals("0")) return icon0;
		if(state.equals("N")) return iconN;
		if(state.equals("D")) return iconD;
		if(state.equals("A")) return iconA;
		if(state.equals("DA")) return iconDA;
		if(state.equals("R")) return iconR;
		if(state.equals("DR")) return iconDR;
		return null;
	}

	private String textForState(String state)
	{
		if(state==null) return "null";
		if(iconForState(state)!=null) return "";
		return state;
	}
	
	private String textForIndex(Integer index)
	{
		if(index==null) return "";
		return " "+index;
	}
}