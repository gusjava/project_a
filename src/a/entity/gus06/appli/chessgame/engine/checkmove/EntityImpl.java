package a.entity.gus06.appli.chessgame.engine.checkmove;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20150410";}


	private Service checkPawn;
	private Service checkRook;
	private Service checkKnight;
	private Service checkBishop;
	private Service checkQueen;
	private Service checkKing;


	public EntityImpl() throws Exception
	{
		checkPawn = Outside.service(this,"gus06.appli.chessgame.engine.checkmove.pawn");
		checkRook = Outside.service(this,"gus06.appli.chessgame.engine.checkmove.rook");
		checkKnight = Outside.service(this,"gus06.appli.chessgame.engine.checkmove.knight");
		checkBishop = Outside.service(this,"gus06.appli.chessgame.engine.checkmove.bishop");
		checkQueen = Outside.service(this,"gus06.appli.chessgame.engine.checkmove.queen");
		checkKing = Outside.service(this,"gus06.appli.chessgame.engine.checkmove.king");
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
		
		int value0 = data[x0][y0];
		int value1 = data[x1][y1];
		
		if(x0==x1 && y0==y1) return false;
		if(value1>0) return false;
		
		switch(value0)
		{
			case 1: return checkPawn.f(obj);
			case 2: return checkRook.f(obj);
			case 3: return checkKnight.f(obj);
			case 4: return checkBishop.f(obj);
			case 5: return checkQueen.f(obj);
			case 6: return checkKing.f(obj);
		}
		return false;
	}
}
