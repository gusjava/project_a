package a.entity.gus06.appli.vindinium.map.viewer.board.changetile;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170917";}


	public Object t(Object obj) throws Exception
	{
		Object[] t = (Object[]) obj;
		
		if(t.length==2) 
		{
			int[][] tiles = (int[][]) t[0];
			int[] pos = (int[]) t[1];

			return process(tiles,pos);
		}
		if(t.length==3) 
		{
			int[][] tiles = (int[][]) t[0];
			int[] pos = (int[]) t[1];
			String type = (String) t[2];

			return process(tiles,pos,type);
		}
		throw new Exception("Wrong data number: "+t.length);
	}


	
	private int[][] process(int[][] tiles, int[] pos)
	{
		if(tiles==null || pos==null) return null;
		
		int x = pos[0];
		int y = pos[1];
		
		int v1 = tiles[x][y];
		int v2 = nextTileValue(v1);
		if(v1==v2) return null;
		
		int n = tiles.length;
		
		tiles[x][y] = v2;
		tiles[x][n-1-y] = v2;
		tiles[n-1-x][y] = v2;
		tiles[n-1-x][n-1-y] = v2;
		
		return tiles;
	}
	
	
	
	private int[][] process(int[][] tiles, int[] pos, String type)
	{
		if(tiles==null || pos==null) return null;
		
		int x = pos[0];
		int y = pos[1];
		
		int v1 = tiles[x][y];
		int v2 = nextTileValue(v1,type);
		if(v1==v2) return null;
		
		int n = tiles.length;
		
		tiles[x][y] = v2;
		tiles[x][n-1-y] = v2;
		tiles[n-1-x][y] = v2;
		tiles[n-1-x][n-1-y] = v2;
		
		return tiles;
	}
	
	
	
	private int nextTileValue(int v)
	{
		switch(v) {
		case TILE.AIR:return TILE.WALL;
		case TILE.WALL:return TILE.MINE;
		case TILE.MINE:return TILE.AIR;
		default:return v;
		}
	}
	
	
	private int nextTileValue(int v, String type)
	{
		if(type.equals("w"))
		{
			if(v==TILE.AIR) return TILE.WALL;
			if(v==TILE.MINE) return TILE.WALL;
		}
		else if(type.equals("a"))
		{
			if(v==TILE.WALL) return TILE.AIR;
			if(v==TILE.MINE) return TILE.AIR;
		}
		else if(type.equals("m"))
		{
			if(v==TILE.AIR) return TILE.MINE;
			if(v==TILE.WALL) return TILE.MINE;
		}
		return v;
	}
}
