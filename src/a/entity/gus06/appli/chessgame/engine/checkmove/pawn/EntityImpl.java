package a.entity.gus06.appli.chessgame.engine.checkmove.pawn;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20150411";}

	/********************************/
	/* 1:pawn, 2:tower, 3:knight    */
	/* 4:bishop, 5:queen, 6:king    */
	/* -:black +:white              */
	/********************************/
	
	
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
		
		
		
		int dy = Math.abs(y1-y0);
		if(dy==0)
		{
			if(x1-x0==-1 && value1==0) return true;
			if(x0==6 && x1==4 && data[5][y1]==0 && data[4][y1]==0) return true;
		}
		if(dy==1)
		{
			if(x1-x0==-1 && value1<0) return true;
			if(x0==3 && x1==2 && data[3][y1]==-1 && data[2][y1]==0 && data[1][y1]==0) return true;
		}
		return false;
	}
}
