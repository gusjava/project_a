package a.entity.gus06.appli.chessgame.engine.checkmove.bishop;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20150411";}

	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		int[][] data = (int[][]) o[0];
		int[] start = (int[]) o[1];
		int[] end = (int[]) o[2];
		
		int x0 = start[0];
		int y0 = start[1];
		
		int x1 = end[0];
		int y1 = end[1];
		
		if(x0==x1 && y0==y1) return false;
		
		int value0 = data[x0][y0];
		int value1 = data[x1][y1];
		
		if(value1>0) return false;
		
		
		
		
		int dx = Math.abs(x1-x0);
		int dy = Math.abs(y1-y0);
		
		if(dy!=dx) return false;
		
		int cx = dx/(x1-x0);
		int cy = dy/(y1-y0);
		
		for(int i=1;i<dx;i++)
			if(data[x0+cx*i][y0+cy*i]!=0) return false;
		return true;
	}
}
