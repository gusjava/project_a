package a.entity.gus06.swing.table.renderer.builder1.g;

import a.framework.*;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import java.util.Map;
import java.util.HashMap;
import javax.swing.JLabel;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191113";}

	
	public Object t(Object obj) throws Exception
	{return new TableCellRenderer1((G) obj);}
	
	
	
	private class TableCellRenderer1 implements TableCellRenderer
	{
		private G g;
		private boolean failed = false;
		private JLabel label = new JLabel("#");
		
		public JLabel label(String text)
		{label.setText(text);return label;}
		
		public TableCellRenderer1(G g){this.g = g;}
		
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		{
			label.setBackground(isSelected ? Color.LIGHT_GRAY : Color.WHITE);
			if(failed) return label("#");
			
			return mapToComp(this);
		}
	}
	
	
	private Component mapToComp(TableCellRenderer1 ren)
	{
		try
		{
			Object value = ren.g.g();
			if(value==null) return ren.label("null");
			
			if(value instanceof Component) return (Component) value;
			if(value instanceof String) return ren.label((String) value);
			if(value instanceof Number) return ren.label(""+value);
			if(value instanceof Boolean) return ren.label(""+value);
			
			throw new Exception("Invalid data type: "+value.getClass().getName());
		}
		catch(Exception e)
		{
			Outside.err(this,"mapToComp(TableCellRenderer1)",e);
			ren.failed = true;
			return ren.label("### "+e.getMessage());
		}
	}
}
