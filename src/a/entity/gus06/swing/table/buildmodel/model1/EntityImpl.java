package a.entity.gus06.swing.table.buildmodel.model1;

import a.framework.*;
import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170412";}


	private Service apply;

	public EntityImpl() throws Exception
	{
		apply = Outside.service(this,"gus06.feature.apply.gobj");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		
		Object data = o[0];
		List columns = (List) o[1];
		Map display = (Map) o[2];
		
		if(data instanceof List) 
			return new TableModel1((List) data,columns,display);
		if(data instanceof Object[][]) 
			return new TableModel2((Object[][]) data,columns,display);
		
		throw new Exception("Invalid data type: "+data.getClass().getName());
	}
	
	
	
	
	
	private class TableModel1 extends AbstractTableModel
	{
		private List data;
		private List columns;
		private Map display;
		
		public TableModel1(List data, List columns, Map display)
		{
			this.data = data;
			this.columns = columns;
			this.display = display;
			
			if(columns==null)
			this.columns = columnsFromData(data);
		}
		
		public int getColumnCount(){return columns.size();}
		public int getRowCount() {return data.size();}
		
		public Class getColumnClass(int y)
		{return Object.class;}
		
		public boolean isCellEditable(int x, int y)
		{
			Object cell = cellAt(x,y);
			return cell!=null && cell instanceof P;
		}
		
		public String getColumnName(int y)
		{
			String col = (String) columns.get(y);
			if(display==null || !display.containsKey(col)) return col;
			return (String) display.get(col);
		}

		public Object getValueAt(int x, int y)
		{
			Object cell = cellAt(x,y);
			return apply(cell);
		}
		
		public void setValueAt(Object value, int x, int y)
		{
			Object cell = cellAt(x,y);
			if(cell!=null && cell instanceof P)
			send((P) cell,value);
			fireTableDataChanged();
		}
		
		private Object cellAt(int x, int y)
		{
			Object row = data.get(x);
			if(row instanceof Map) return cellFromMap((Map) row, y);
			if(row instanceof List) return cellFromList((List) row, y);
			if(row instanceof Object[]) return cellFromArray((Object[]) row, y);
			return null;
		}
		
		private Object cellFromMap(Map map, int y)
		{
			String col = (String) columns.get(y);
			if(!map.containsKey(col)) return null;
			return map.get(col);
		}
		
		private Object cellFromList(List list, int y)
		{
			return list.size()>y ? list.get(y) : null;
		}
		
		private Object cellFromArray(Object[] array, int y)
		{
			return array.length>y ? array[y] : null;
		}
	}
	
	
	
	
	
	
	
	private class TableModel2 extends AbstractTableModel
	{
		private Object[][] data;
		private List columns;
		private Map display;
		
		public TableModel2(Object[][] data, List columns, Map display) throws Exception
		{
			this.data = data;
			this.columns = columns;
			this.display = display;
			
			if(columns==null) throw new Exception("Failed to infer columns");
		}
		
		public int getColumnCount(){return columns.size();}
		public int getRowCount() {return data.length;}
		
		public Class getColumnClass(int y)
		{return Object.class;}
		
		public boolean isCellEditable(int x, int y)
		{
			Object cell = cellAt(x,y);
			return cell!=null && cell instanceof P;
		}
		
		public String getColumnName(int y)
		{
			String col = (String) columns.get(y);
			if(display==null || !display.containsKey(col)) return col;
			return (String) display.get(col);
		}

		public Object getValueAt(int x, int y)
		{
			Object cell = cellAt(x,y);
			return apply(cell);
		}
		
		public void setValueAt(Object value, int x, int y)
		{
			Object cell = cellAt(x,y);
			if(cell!=null && cell instanceof P)
			send((P) cell,value);
			fireTableDataChanged();
		}
		
		private Object cellAt(int x, int y)
		{
			Object[] row = data[x];
			return row.length>y ? row[y] : null;
		}
	}
	
	
	
	
	
	private List columnsFromData(List data)
	{
		if(data.isEmpty()) return null;
		Map m = (Map) data.get(0);
		List l = new ArrayList(m.keySet());
		Collections.sort(l);
		return l;
	}
	
	private Object apply(Object value)
	{
		try{return apply.t(value);}
		catch(Exception e) {Outside.err(this,"apply(Object)",e);}
		return null;
	}
	
	private void send(P p, Object value)
	{
		try{p.p(value);}
		catch(Exception e) {Outside.err(this,"e(P,Object)",e);}
	}
}
