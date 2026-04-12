package a.entity.gus06.appli.chessgame.data.move;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150410";}

	/********************************/
	/* 1:pawn, 2:tower, 3:knight    */
	/* 4:bishop, 5:queen, 6:king    */
	/* -:black +:white              */
	/********************************/

	private Service moveKingWhite;
	private Service moveKingBlack;
	
	private Service movePawnWhite;
	private Service movePawnBlack;
	
	
	
	public EntityImpl() throws Exception
	{
		moveKingWhite = Outside.service(this,"gus06.appli.chessgame.data.move.king.white");
		moveKingBlack = Outside.service(this,"gus06.appli.chessgame.data.move.king.black");
		
		movePawnWhite = Outside.service(this,"gus06.appli.chessgame.data.move.pawn.white");
		movePawnBlack = Outside.service(this,"gus06.appli.chessgame.data.move.pawn.black");
	}


	
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
		
		int value0 = data[x0][y0];
		
		if(value0==6)	{moveKingWhite.p(obj);return;}
		if(value0==-6)	{moveKingBlack.p(obj);return;}
		
		if(value0==1)	{movePawnWhite.p(obj);return;}
		if(value0==-1)	{movePawnBlack.p(obj);return;}
		
		data[x1][y1] = value0;
		data[x0][y0] = 0;
	}
}
