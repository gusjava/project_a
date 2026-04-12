package a.entity.gus06.appli.chessgame.data.move.king.white;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150411";}

	/********************************/
	/* 1:pawn, 2:tower, 3:knight    */
	/* 4:bishop, 5:queen, 6:king    */
	/* -:black +:white              */
	/********************************/
	
	
	public void p(Object obj) throws Exception
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
		
		if(y1-y0==2) // petit roque
		{
			data[7][4] = 0;
			data[7][5] = 2;
			data[7][6] = 6;
			data[7][7] = 0;
		}
		else if(y1-y0==-2) // grand roque
		{
			data[7][4] = 0;
			data[7][3] = 2;
			data[7][2] = 6;
			data[7][1] = 0;
			data[7][0] = 0;
		}
		else
		{
			data[x1][y1] = data[x0][y0];
			data[x0][y0] = 0;
		}
	}
}
