package a.entity.gus06.appli.vindinium.engine.board.decode;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170923";}


	public Object t(Object obj) throws Exception
	{
		String tiles = (String) obj;
		int length = tiles.length();
		int size = (int) Math.sqrt(length/2);
		
		int[][] board = new int[size][size];
		
		char[] buff = tiles.toCharArray();
		int index = 0;
		
		for(int i=0;i<size;i++) for(int j=0;j<size;j++)
		{
			int tileValue = getTileValue(buff,index);
			index += 2;
			board[i][j] = tileValue;
		}
		
		return board;
	}
	
	
	
	private int getTileValue(char[] buff, int index) throws Exception
	{
		char c1 = buff[index];
		char c2 = buff[index+1];
		
		if(c1=='#') return TILE.WALL;
		if(c1=='[') return TILE.TAVERN;
		if(c1==' ') return TILE.AIR;
		
		if(c1=='@')
		{
			if(c2=='1') return TILE.HERO1;
			if(c2=='2') return TILE.HERO2;
			if(c2=='3') return TILE.HERO3;
			if(c2=='4') return TILE.HERO4;
		}
		if(c1=='$')
		{
			if(c2=='1') return TILE.MINE1;
			if(c2=='2') return TILE.MINE2;
			if(c2=='3') return TILE.MINE3;
			if(c2=='4') return TILE.MINE4;
			return TILE.MINE;
		}
		throw new Exception("Invalid tile data: "+c1+c2);
	}
}
