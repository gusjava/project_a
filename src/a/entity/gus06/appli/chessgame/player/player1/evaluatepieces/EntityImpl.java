package a.entity.gus06.appli.chessgame.player.player1.evaluatepieces;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150418";}
	
	public static final int[] VALUES = new int[]{0,1,5,3,3,9,0};

	
	
	public Object t(Object obj) throws Exception
	{
		int[][] board = (int[][]) obj;
		double s = 0;
		
		for(int i=0;i<8;i++)for(int j=0;j<8;j++)
		s += valueFor(board[i][j]);
		
		return Double.valueOf(s);
	}
	
	private double valueFor(int piece)
	{
		if(piece>=0) return VALUES[piece];
		return -VALUES[-piece];
	}
}
