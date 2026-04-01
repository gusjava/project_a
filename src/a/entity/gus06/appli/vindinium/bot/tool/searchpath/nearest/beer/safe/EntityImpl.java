package a.entity.gus06.appli.vindinium.bot.tool.searchpath.nearest.beer.safe;

import java.util.List;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170923";}


	private Service bfs;

	public EntityImpl() throws Exception
	{
		bfs = Outside.service(this,"gus06.math.maze.solve.algo.breadthfirstsearch.many.trans");
	}


	public Object t(Object obj) throws Exception
	{
		Map data = (Map) obj;
		
		List beers = (List) data.get(DATA_._BEER);
		List enemies = (List) data.get(DATA_._ENEMY);
		boolean[][] maze = (boolean[][]) data.get(DATA_._MAZE);
		int[] me_pos = (int[]) data.get(DATA_ME_._ME_POS);
		
		boolean[][] newMaze = buildNewMaze(maze,enemies,me_pos);
		return (int[][]) bfs.t(new Object[]{newMaze,me_pos,beers});
	}
	
	
	private boolean[][] buildNewMaze(boolean[][] maze, List enemies, int[] me_pos)
	{
		int size = maze.length;
		boolean[][] m = new boolean[size][size];
		
		for(int i=0;i<size;i++) for(int j=0;j<size;j++)
		m[i][j] = maze[i][j];
		
		for(int i=0;i<enemies.size();i++)
		fillEnemy(m,(int[]) enemies.get(i));
		
		m[me_pos[0]][me_pos[1]] = true;
		
		return m;
	}
	
	
	private void fillEnemy(boolean[][] m, int[] pos)
	{
		int x = pos[0];
		int y = pos[1];
		int size = m.length;
		
		m[x][y] = false;
		
		if(x>0) m[x-1][y] = false;
		if(x<size-1) m[x+1][y] = false;
		
		if(y>0) m[x][y-1] = false;
		if(y<size-1) m[x][y+1] = false;
		
//		if(x>0 && y>0) m[x-1][y-1] = false;
//		if(x>0 && y<size-1) m[x-1][y+1] = false;
//
//		if(x<size-1 && y>0) m[x+1][y-1] = false;
//		if(x<size-1 && y<size-1) m[x+1][y+1] = false;
	}
}
