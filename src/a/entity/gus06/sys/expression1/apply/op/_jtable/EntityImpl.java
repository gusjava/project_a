package a.entity.gus06.sys.expression1.apply.op._jtable;

import a.framework.*;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.Action;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.TableCellRenderer;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170412";}

	public final static String KEY_DATA = "data";
	public final static String KEY_COL = "col";
	public final static String KEY_COL_DISPLAY = "col_display";
	public final static String KEY_COL_SIZE = "col_size";
	public final static String KEY_ROW_HEIGHT = "row_height";
	public final static String KEY_RENDERER = "renderer";
	



	private Service buildModel;
	private Service buildRenderer;
	private Service rendering1;
	
	public EntityImpl() throws Exception
	{
		buildModel = Outside.service(this,"gus06.swing.table.buildmodel.model1");
		buildRenderer = Outside.service(this,"gus06.swing.table.renderer.builder1");
		rendering1 = Outside.service(this,"gus06.swing.table.cust.renderer.default1");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Map) return buildTableFromMap((Map) obj);
		if(obj instanceof List) return buildTableFromData(obj);
		if(obj instanceof Object[][]) return buildTableFromData(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	
	private JTable buildTableFromMap(Map map) throws Exception
	{
		Object data = get(map,KEY_DATA);
		List col = (List) get(map,KEY_COL);
		Map colDisplay = (Map) get(map,KEY_COL_DISPLAY);
		Map colSize = (Map) get(map,KEY_COL_SIZE);
		Integer rowHeight = (Integer) get(map,KEY_ROW_HEIGHT);
		Object rendererObj = get(map,KEY_RENDERER);
		
		if(data==null) throw new Exception("Data not defined for JTable");
		if(col==null)
		{
			if(data instanceof List)
			col = columnsFromData((List) data);
			else throw new Exception("Failed to infer column names from data");
		}
		
		TableModel model = (TableModel) buildModel.t(new Object[]{data,col,colDisplay});
		JTable table = new JTable(model);
		
		TableCellRenderer renderer = (TableCellRenderer) buildRenderer.t(rendererObj);
		if(renderer!=null) table.setDefaultRenderer(Object.class,renderer);
		else rendering1.p(table);
		
		if(colSize!=null) initColSize(table,col,colSize);
		if(rowHeight!=null) initRowHeight(table,rowHeight);
		
		return table;
	}
	
	
	private JTable buildTableFromData(Object data) throws Exception
	{
		TableModel model = (TableModel) buildModel.t(new Object[]{data,null,null});
		JTable table = new JTable(model);
		rendering1.p(table);
		return table;
	}
	
	
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
	
	
	private List columnsFromData(List data)
	{
		if(data.isEmpty()) return null;
		Map m = (Map) data.get(0);
		List l = new ArrayList(m.keySet());
		Collections.sort(l);
		return l;
	}
	
	
	private void initColSize(JTable table, List col, Map colSize)
	{
		for(int i=0;i<col.size();i++)
		{
			String n = (String) col.get(i);
			if(colSize.containsKey(n))
			{
				Integer size = (Integer) colSize.get(n);
				setColumnSize(table,i,size.intValue());
			}
		}
	}
	
	private void setColumnSize(JTable table, int index, int size)
	{
		table.getColumnModel().getColumn(index).setMaxWidth(size);
		table.getColumnModel().getColumn(index).setMinWidth(size);
	}
	
	private void initRowHeight(JTable table, Integer rowHeight)
	{
		table.setRowHeight(rowHeight.intValue());
	}
	
}
