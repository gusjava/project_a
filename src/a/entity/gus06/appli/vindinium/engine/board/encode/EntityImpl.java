package a.entity.gus06.appli.vindinium.engine.board.encode;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170923";}


	public Object t(Object obj) throws Exception
	{
		int[][] board = (int[][]) obj;
		int size = board.length;
		
		StringBuffer tiles = new StringBuffer();
		for(int i=0;i<size;i++) for(int j=0;j<size;j++)
		{
			int tileValue = board[i][j];
			tiles.append(tileToString(tileValue));
		}
		return tiles.toString();
	}

	private String tileToString(int v) throws Exception
	{
		switch(v) {
		case TILE.AIR:return "  ";
		case TILE.HERO1:return "@1";
		case TILE.HERO2:return "@2";
		case TILE.HERO3:return "@3";
		case TILE.HERO4:return "@4";
		case TILE.TAVERN:return "[]";
		case TILE.WALL:return "##";
		case TILE.MINE:return "$-";
		case TILE.MINE1:return "$1";
		case TILE.MINE2:return "$2";
		case TILE.MINE3:return "$3";
		case TILE.MINE4:return "$4";
		default: throw new Exception("Invalid tile value: "+v);
		}
	}
}
