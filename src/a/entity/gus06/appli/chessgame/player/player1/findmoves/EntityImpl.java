package a.entity.gus06.appli.chessgame.player.player1.findmoves;

import a.framework.*;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150417";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public Object t(Object obj) throws Exception
	{
		int[][] board = (int[][]) obj;
		Set set = new HashSet();
		
		for(int i=0;i<8;i++)for(int j=0;j<8;j++)
		if(board[i][j]>0) set.addAll(findMoves(board,i,j));
		
		return set;
	}
	
	
	private Set findMoves(int[][] board, int i, int j)
	{
		int value = board[i][j];
		
		switch(value) {
			case 1:return findMoves_pawn(board,i,j);
		}
		return new HashSet();
	}
	
	
	
	
	private Set findMoves_pawn(int[][] board, int i, int j)
	{
		return new HashSet();
	}
}
