package a.entity.gus06.swing.table.renderer.builder1.map;

import a.framework.*;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import java.util.Map;
import javax.swing.JLabel;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191112";}


	private Service build;
	private Service buildDefault;
	
	public EntityImpl() throws Exception
	{
		build = Outside.service(this,"gus06.swing.table.renderer.builder1.a");
		buildDefault = Outside.service(this,"gus06.swing.table.renderer.default1");
	}


	
	public Object t(Object obj) throws Exception
	{
		TableCellRenderer defaultRenderer = (TableCellRenderer) buildDefault.g();
		return new TableCellRenderer1((Map) obj,defaultRenderer);
	}
	
	
	
	private class TableCellRenderer1 implements TableCellRenderer
	{
		private Map map;
		private TableCellRenderer defaultRenderer;
		
		private boolean failed = false;
		private JLabel label = new JLabel("#");
		
		public JLabel label(String text)
		{label.setText(text);return label;}
		
		public TableCellRenderer1(Map map, TableCellRenderer defaultRenderer)
		{
			this.map = map;
			this.defaultRenderer = defaultRenderer;
		}
		
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		{
			if(failed) return label("#");
			
			String columnName = table.getColumnName(column);
			if(!map.containsKey(columnName)) 
			return defaultRenderer.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);
			
			try
			{
				Object r = map.get(columnName);
				TableCellRenderer renderer = (TableCellRenderer) build.t(r);
				if(renderer==null) return label("null");
				return renderer.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);
			}
			catch(Exception e)
			{
				Outside.err(EntityImpl.this,"getTableCellRendererComponent(JTable,Object,boolean,boolean,int,int)",e);
				failed = true;
				return label("### "+e.getMessage());
			}
		}
	}
}
