package a.entity.gus06.appli.vindinium.map.viewer.board.rebuild;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170917";}


	public void p(Object obj) throws Exception
	{
		int[][] tiles = (int[][]) obj;
		
		int n = tiles.length;
		
		for(int i=0;i<n/2;i++) for(int j=0;j<n/2;j++)
		{
			int v = tiles[i][j];
			tiles[n-1-i][j] = changeTile(2,v);
			tiles[n-1-i][n-1-j] = changeTile(3,v);
			tiles[i][n-1-j] = changeTile(4,v);
		}
	}

	private int changeTile(int area, int value)
	{
		if(value==TILE.HERO1) return area;
		return value;
	}
}
