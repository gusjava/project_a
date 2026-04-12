package a.entity.gus06.appli.chessgame.data.move.pawn.white;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150417";}

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
		
		if(y1!=y0 && x0==3 && x1==2 && data[2][y1]==0 && data[3][y1]==-1) // prise en passant
		{
			data[x1][y1] = data[x0][y0];
			data[x0][y0] = 0;
			data[x0][y1] = 0;
		}
		else
		{
			data[x1][y1] = data[x0][y0];
			data[x0][y0] = 0;
		}
	}
}
