package a.entity.gus06.array.d2.objectarray.cell.d4;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180328";}


	private Service ruleToIndex;
	
	public EntityImpl() throws Exception
	{
		ruleToIndex = Outside.service(this,"gus06.list.ruletoindex");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object[][] input = (Object[][]) o[0];
		Object xRule = o[1];
		Object yRule = o[2];
		
		int nb1 = input.length;
		int nb2 = nb1>0 ? input[0].length : 0;
		
		Integer x = (Integer) ruleToIndex.t(new Object[]{nb1,xRule});
		Integer y = (Integer) ruleToIndex.t(new Object[]{nb2,yRule});
		
		int i = x.intValue();
		int j = y.intValue();
		
		boolean firstRow = i==0;
		boolean lastRow = i==nb1-1;
		
		boolean firstCol = j==0;
		boolean lastCol = j==nb2-1;
		
		Object cellN = firstRow ? null : input[i-1][j];
		Object cellS = lastRow ? null : input[i+1][j];
		
		Object cellW = firstCol ? null : input[i][j-1];
		Object cellE = lastCol ? null : input[i][j+1];
		
		List d4 = new ArrayList();
		d4.add(cellN);
		d4.add(cellE);
		d4.add(cellS);
		d4.add(cellW);
		
		return d4;
	}
}
