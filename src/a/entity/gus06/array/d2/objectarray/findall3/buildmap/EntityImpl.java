package a.entity.gus06.array.d2.objectarray.findall3.buildmap;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180115";}

	private Service findArray2;
	
	public EntityImpl() throws Exception
	{
		findArray2 = Outside.service(this,"gus06.find.objectarray2");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object[][] input = (Object[][]) findArray2.t(o[0]);
		Integer index1 = (Integer) o[1];
		Integer index2 = (Integer) o[2];
		
		
		int i = index1.intValue();
		int j = index2.intValue();
		
		int nb1 = input.length;
		int nb2 = nb1>0 ? input[0].length : 0;
		
		boolean firstRow = i==0;
		boolean lastRow = i==nb1-1;
		
		boolean firstCol = j==0;
		boolean lastCol = j==nb2-1;
		
		Object cell = input[i][j];
		Object[] row = input[i];
		
		Object previous = firstCol ? (firstRow ? null : input[i-1][nb2-1]) : input[i][j-1];
		Object next = lastCol ? (lastRow ? null : input[i+1][0]) : input[i][j+1];
		
		Object cellN = firstRow ? null : input[i-1][j];
		Object cellS = lastRow ? null : input[i+1][j];
		
		Object cellW = firstCol ? null : input[i][j-1];
		Object cellE = lastCol ? null : input[i][j+1];
		
		Object cellNW = firstCol || firstRow ? null : input[i-1][j-1];
		Object cellNE = lastCol || firstRow ? null : input[i-1][j+1];
		
		Object cellSW = firstCol || lastRow ? null : input[i+1][j-1];
		Object cellSE = lastCol || lastRow ? null : input[i+1][j+1];
		
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
		
		Map m = new HashMap();
		
		put(m,"matrix",input);
		
		put(m,"row_i",Integer.valueOf(i));
		put(m,"col_i",Integer.valueOf(j));
		
		put(m,"row_nb",Integer.valueOf(nb1));
		put(m,"col_nb",Integer.valueOf(nb2));
		
		put(m,"cell",cell);
		put(m,"row",row);
		
		put(m,"next",next);
		put(m,"previous",previous);
		
		put(m,"N",cellN);
		put(m,"S",cellS);
		put(m,"E",cellE);
		put(m,"W",cellW);
		
		put(m,"NW",cellNW);
		put(m,"NE",cellNE);
		put(m,"SW",cellSW);
		put(m,"SE",cellSE);
		
		put(m,"D4",d4);
		put(m,"D8",d8);
		
		put(m,"row_next",getRow(input,i+1));
		put(m,"row_next2",getRow(input,i+2));
		put(m,"row_next3",getRow(input,i+3));
		
		put(m,"row_previous",getRow(input,i-1));
		put(m,"row_previous2",getRow(input,i-2));
		put(m,"row_previous3",getRow(input,i-3));
			
		return m;
	}
	
	
	private void put(Map map, String key, Object value)
	{
		if(value!=null) map.put(key,value);
	}
	
	
	private Object[] getRow(Object[][] array, int index)
	{
		if(index<0 || index>=array.length) return null;
		return array[index];
	}
}
