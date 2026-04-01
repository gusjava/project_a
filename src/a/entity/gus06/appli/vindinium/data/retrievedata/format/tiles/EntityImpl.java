package a.entity.gus06.appli.vindinium.data.retrievedata.format.tiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170923";}

	private Map data;
	

	private void put(String key, Object value)
	{data.put(key,value);}


	public void p(Object obj) throws Exception
	{
		if(obj==null) return;
		data = (Map) obj;
		
		Map game = (Map) data.get(DATA.K_GAME);
		Map me = (Map) data.get(DATA.K_HERO);
		
		int me_id = i_(me.get(DATA.H_ID));
		
		Map board = (Map) game.get(DATA.G_BOARD);
		
		int size = i_(board.get(DATA.B_SIZE));
		String tiles = (String) board.get(DATA.B_TILES);
		
		int tiles_length = tiles.length();
		if(tiles_length!=2*size*size) throw new Exception("Invalid tiles data length: "+tiles_length);
		
		int[][] _board = new int[size][size];
		boolean[][] _maze = new boolean[size][size];
		
		List _air = new ArrayList();
		List _wall = new ArrayList();
		List _beer = new ArrayList();
		List _mine = new ArrayList();
		List _mine_me = new ArrayList();
		List _mine_free = new ArrayList();
		List _mine_target = new ArrayList();
		List _enemy = new ArrayList();
		
		char[] buff = tiles.toCharArray();
		int index = 0;
		
		for(int i=0;i<size;i++) for(int j=0;j<size;j++)
		{
			int tileValue = getTileValue(buff,index);
			index += 2;
			
			_board[i][j] = tileValue;
			_maze[i][j] = !TILE.isFixed(tileValue);
			
			
			if(!TILE.isFixed(tileValue))
				_air.add(p(i,j));
			
			if(TILE.isWall(tileValue))
				_wall.add(p(i,j));
			
			if(TILE.isTavern(tileValue))
				_beer.add(p(i,j));
			
			if(TILE.isMine(tileValue))
				_mine.add(p(i,j));
			
			if(TILE.isFreeMine(tileValue))
				_mine_free.add(p(i,j));
			
			if(TILE.isMyMine(tileValue,me_id))
				_mine_me.add(p(i,j));
			
			if(TILE.isTargetMine(tileValue,me_id))
				_mine_target.add(p(i,j));
			
			if(TILE.isEnemy(tileValue,me_id))
				_enemy.add(p(i,j));
		}
		
		put(DATA_._BOARD,_board);
		put(DATA_._MAZE,_maze);
		
		put(DATA_._AIR,_air);
		put(DATA_._WALL,_wall);
		put(DATA_._BEER,_beer);
		put(DATA_._MINE,_mine);
		put(DATA_._MINE_ME,_mine_me);
		put(DATA_._MINE_FREE,_mine_free);
		put(DATA_._MINE_TARGET,_mine_target);
		put(DATA_._ENEMY,_enemy);
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
	
	
	private int i_(Object s)
	{
		if(s==null) return -1;
		return Integer.parseInt((String) s);
	}
	
	private int[] p(int x, int y)
	{return new int[]{x,y};}
}
