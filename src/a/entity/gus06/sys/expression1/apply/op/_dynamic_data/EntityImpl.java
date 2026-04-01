package a.entity.gus06.sys.expression1.apply.op._dynamic_data;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191114";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof List) return handleList((List) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private List handleList(List list0)
	{
		List list1 = new ArrayList();
		for(int i=0;i<list0.size();i++)
		{
			List row0 = (List) list0.get(i);
			List row1 = new ArrayList();
			list1.add(row1);
			
			for(int j=0;j<row0.size();j++)
			{
				Object cell0 = row0.get(j);
				G cell1 = handleCell(cell0,list1,row1,i,j);
				row1.add(cell1);
			}
		}
		return list1;
	}
	
	
	
	private G handleCell(Object cell0, List list1, List row1, int i, int j)
	{
		if(cell0==null) return new GStatic(null);
		if(cell0 instanceof G) return (G) cell0;
		if(cell0 instanceof T) return new GDynamic((T) cell0,list1,row1,i,j);
		return new GStatic(cell0);
	}
	
	
	private class GStatic implements G
	{
		private Object data;
		public GStatic(Object data) {this.data = data;}
		public Object g() throws Exception {return data;}
	}
	
	
	private class GDynamic implements G
	{
		private T t;
		private List list1;
		private List row1;
		private int i;
		private int j;
		
		private boolean running = false;
		
		public GDynamic(T t, List list1, List row1, int i, int j)
		{
			this.t = t;
			this.list1 = list1;
			this.row1 = row1;
			this.i = i;
			this.j = j;
		}
		
		public Object g() throws Exception
		{
			if(running) throw new Exception("Loop detected");
			
			running = true;
			Map map = buildMap(list1,row1,i,j);
			Object result = t.t(map);
			running = false;
			
			return result;
		}
	}
	
	
	
	
	private Map buildMap(List list1, List row1, int i, int j)
	{
		Object cellN = get(list1,i-1,j);
		Object cellN2 = get(list1,i-2,j);
		Object cellN3 = get(list1,i-3,j);
		
		Object cellS = get(list1,i+1,j);
		Object cellS2 = get(list1,i+2,j);
		Object cellS3 = get(list1,i+3,j);
		
		Object cellW = get(list1,i,j-1);
		Object cellW2 = get(list1,i,j-2);
		Object cellW3 = get(list1,i,j-3);
		
		Object cellE = get(list1,i,j+1);
		Object cellE2 = get(list1,i,j+2);
		Object cellE3 = get(list1,i,j+3);
		
		Object cellNW = get(list1,i-1,j-1);
		Object cellNE = get(list1,i-1,j+1);
		
		Object cellSW = get(list1,i+1,j-1);
		Object cellSE = get(list1,i+1,j+1);
		
		List d4 = new ArrayList();
		d4.add(cellN);
		d4.add(cellE);
		d4.add(cellS);
		d4.add(cellW);
		
		List d8 = new ArrayList();
		d8.add(cellN);
		d8.add(cellNE);
		d8.add(cellE);
		d8.add(cellSE);
		d8.add(cellS);
		d8.add(cellSW);
		d8.add(cellW);
		d8.add(cellNW);
		
		
		Map map = new HashMap();
		
		map.put("data",list1);
		map.put("i",Integer.valueOf(i));
		map.put("j",Integer.valueOf(j));
		
		map.put("row",row1);
		map.put("row_nb",Integer.valueOf(row1.size()));
		
		map.put("row_next",get(list1,i+1));
		map.put("row_next2",get(list1,i+2));
		map.put("row_next3",get(list1,i+3));
		
		map.put("row_previous",get(list1,i-1));
		map.put("row_previous2",get(list1,i-2));
		map.put("row_previous3",get(list1,i-3));
		
		map.put("N",cellN);
		map.put("N2",cellN2);
		map.put("N3",cellN3);
		
		map.put("S",cellS);
		map.put("S2",cellS2);
		map.put("S3",cellS3);
		
		map.put("E",cellE);
		map.put("E2",cellE2);
		map.put("E3",cellE3);
		
		map.put("W",cellW);
		map.put("W2",cellW2);
		map.put("W3",cellW3);
		
		map.put("NW",cellNW);
		map.put("NE",cellNE);
		map.put("SW",cellSW);
		map.put("SE",cellSE);
		
		map.put("D4",d4);
		map.put("D8",d8);
		
		return map;
	}
	
	
	private Object get(List list, int index)
	{
		if(index<0) return null;
		if(index>=list.size()) return null;
		return list.get(index);
	}
	
	private Object get(List list, int i, int j)
	{
		List row = (List) get(list,i);
		return row!=null ? get(row,j) : null;
	}
}
