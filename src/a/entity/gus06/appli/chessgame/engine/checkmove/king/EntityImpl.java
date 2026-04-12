package a.entity.gus06.appli.chessgame.engine.checkmove.king;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20150411";}

	/********************************/
	/* 1:pawn, 2:tower, 3:knight    */
	/* 4:bishop, 5:queen, 6:king    */
	/* -:black +:white              */
	/********************************/

	private Service isSafeCell;

	public EntityImpl() throws Exception
	{
		isSafeCell = Outside.service(this,"gus06.appli.chessgame.data.issafe.forme.cell");
	}

	
	
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
		
		
		
		if(x0==7 && x1==7 && y0==4 && y1==6) // petit roque
			return data[7][5]==0 
			&& data[7][6]==0 
			&& data[7][7]==2 
			&& isSafe(data,7,5) 
			&& isSafe(data,7,6);
		
		if(x0==7 && x1==7 && y0==4 && y1==2) // grand roque
			return data[7][0]==2 
			&& data[7][1]==0 
			&& data[7][2]==0 
			&& data[7][3]==0
			&& isSafe(data,7,2) 
			&& isSafe(data,7,3);
		
		int dx = Math.abs(x1-x0);
		int dy = Math.abs(y1-y0);
		
		return dx<2 && dy<2;
	}
	
	
	
	private boolean isSafe(int[][] data, int x, int y) throws Exception
	{return isSafeCell.f(new Object[]{data,new int[]{x,y}});}
}
