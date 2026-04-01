package a.entity.gus06.swing.table.renderer.builder1.t;

import a.framework.*;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import java.util.Map;
import java.util.HashMap;
import javax.swing.JLabel;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170414";}


	private Service custLabel;
	
	public EntityImpl() throws Exception
	{
		custLabel = Outside.service(this,"gus06.swing.label.cust3.map1");
	}


	
	public Object t(Object obj) throws Exception
	{return new TableCellRenderer1((T) obj);}
	
	
	
	private class TableCellRenderer1 implements TableCellRenderer
	{
		private T t;
		private boolean failed = false;
		private JLabel label = new JLabel("#");
		
		public JLabel label(String text)
		{label.setText(text);return label;}
		
		public TableCellRenderer1(T t){this.t = t;}
		
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		{
			label.setBackground(isSelected ? Color.LIGHT_GRAY : Color.WHITE);
			if(failed) return label("#");
			
			Map m = new HashMap();
			m.put("table",table);
			m.put("value",value);
			m.put("isSelected",Boolean.valueOf(isSelected));
			m.put("hasFocus",Boolean.valueOf(hasFocus));
			m.put("row",Integer.valueOf(row));
			m.put("column",Integer.valueOf(column));
			
			return mapToComp(this,m);
		}
	}
	
	
	private Component mapToComp(TableCellRenderer1 ren, Map m)
	{
		try
		{
			Object value = ren.t.t(m);
			if(value==null) return ren.label("null");
			
			if(value instanceof Component) return (Component) value;
			if(value instanceof String) return ren.label((String) value);
			if(value instanceof Number) return ren.label(""+value);
			if(value instanceof Boolean) return ren.label(""+value);
			if(value instanceof Boolean) return ren.label(""+value);
			
			if(value instanceof Map)
			{
				custLabel.p(new Object[]{ren.label,value});
				return ren.label;
			}
			
			throw new Exception("Invalid data type: "+value.getClass().getName());
		}
		catch(Exception e)
		{
			Outside.err(this,"mapToComp(TableCellRenderer1,Map)",e);
			ren.failed = true;
			return ren.label("### "+e.getMessage());
		}
	}
}
