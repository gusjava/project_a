package a.entity.gus06.appli.vindinium.engine.handleturn;

public class Turn1 extends Turn0 {

	
	protected int[][] tiles;
	protected String[] direction;
	
	
	
	protected void initDirection(Object obj)
	{direction = (String[]) obj;}
	
	
	
	protected int[] checkPos(int[] pos)
	{
		int size = tiles.length;
		if(pos[0]<0 || pos[0]>=size || pos[1]<0 || pos[1]>=size) return null;
		return pos;
	}
	
	
	
	protected int[] findNextPos(String d, int[] pos)
	{
		if(d==null) return null;
		if(d.equals(DIRECTION.STAY)) return null;
		if(d.equals(DIRECTION.NORTH)) return checkPos(new int[]{pos[0]-1,pos[1]});
		if(d.equals(DIRECTION.SOUTH)) return checkPos(new int[]{pos[0]+1,pos[1]});
		if(d.equals(DIRECTION.WEST)) return checkPos(new int[]{pos[0],pos[1]-1});
		if(d.equals(DIRECTION.EAST)) return checkPos(new int[]{pos[0],pos[1]+1});
		return null;
	}
	
	
	
	
	
	
	protected int getTileCode(int[] pos)
	{
		if(pos==null) return -1;
		return tiles[pos[0]][pos[1]];
	}
	
	
	protected void setTileCode(int[] pos, int code)
	{tiles[pos[0]][pos[1]] = code;}
	
	
	
	
	
	protected int countTiles(int tileCode)
	{
		int n = 0;
		int size = tiles.length;
		for(int i=0;i<size;i++) for(int j=0;j<size;j++) if(tiles[i][j]==tileCode) n++;
		return n;
	}
	
	
	protected void exchangeTiles(int[] pos1, int[] pos2)
	{
		int v1 = tiles[pos1[0]][pos1[1]];
		int v2 = tiles[pos2[0]][pos2[1]];
		
		tiles[pos1[0]][pos1[1]] = v2;
		tiles[pos2[0]][pos2[1]] = v1;
	}
	
	
	protected void updateTiles(int oldCode, int newCode)
	{
		int size = tiles.length;
		for(int i=0;i<size;i++) for(int j=0;j<size;j++)
			if(tiles[i][j]==oldCode) tiles[i][j] = newCode;
	}
}
