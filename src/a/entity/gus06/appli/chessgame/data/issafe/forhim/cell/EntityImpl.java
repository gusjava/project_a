package a.entity.gus06.appli.chessgame.data.issafe.forhim.cell;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20150412";}

	private Service checkPawn;
	private Service checkRook;
	private Service checkKnight;
	private Service checkBishop;
	private Service checkQueen;
	
	
	public EntityImpl() throws Exception
	{
		checkPawn = Outside.service(this,"gus06.appli.chessgame.engine.checkmove.pawn");
		checkRook = Outside.service(this,"gus06.appli.chessgame.engine.checkmove.rook");
		checkKnight = Outside.service(this,"gus06.appli.chessgame.engine.checkmove.knight");
		checkBishop = Outside.service(this,"gus06.appli.chessgame.engine.checkmove.bishop");
		checkQueen = Outside.service(this,"gus06.appli.chessgame.engine.checkmove.queen");
	}
	
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		int[][] data = (int[][]) o[0];
		int[] cell = (int[]) o[1];
		
		for(int i=0;i<8;i++)for(int j=0;j<8;j++)
		{
			int value = data[i][j];
			if(value>0)
			{
				int[] start = new int[]{i,j};
				switch(value)
				{
					case 1: if(checkPawn(data,start,cell)) return false;break;
					case 2: if(checkRook(data,start,cell)) return false;break;
					case 3: if(checkKnight(data,start,cell)) return false;break;
					case 4: if(checkBishop(data,start,cell)) return false;break;
					case 5: if(checkQueen(data,start,cell)) return false;break;
					case 6: if(checkKing(data,start,cell)) return false;break;
				}
			}
		}
		return true;
	}
	
	
	
	
	
	
	private boolean checkPawn(int[][] data, int[] start, int[] end) throws Exception
	{return checkPawn.f(new Object[]{data,start,end});}
	
	private boolean checkRook(int[][] data, int[] start, int[] end) throws Exception
	{return checkRook.f(new Object[]{data,start,end});}
	
	private boolean checkKnight(int[][] data, int[] start, int[] end) throws Exception
	{return checkKnight.f(new Object[]{data,start,end});}
	
	private boolean checkBishop(int[][] data, int[] start, int[] end) throws Exception
	{return checkBishop.f(new Object[]{data,start,end});}
	
	private boolean checkQueen(int[][] data, int[] start, int[] end) throws Exception
	{return checkQueen.f(new Object[]{data,start,end});}
	
	
	private boolean checkKing(int[][] data, int[] start, int[] end)
	{
		int dx = Math.abs(start[0]-end[0]);
		int dy = Math.abs(start[1]-end[1]);
		return dx<2 && dy<2;
	}
}
