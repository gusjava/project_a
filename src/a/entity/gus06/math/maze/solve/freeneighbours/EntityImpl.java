package a.entity.gus06.math.maze.solve.freeneighbours;

import java.util.ArrayList;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170923";}


	public Object t(Object obj) throws Exception
	{
		Object[] t = (Object[]) obj;
		if(t.length!=2) throw new Exception("Wrong data number: "+t.length);
		
		boolean[][] maze = (boolean[][]) t[0];
		int[] pos = (int[]) t[1];
		
		return freeNeighbours(maze,pos);
	}

	
	
	private List freeNeighbours(boolean[][] maze, int[] p)
	{
		int x = p[0];
		int y = p[1];
		
		List l = new ArrayList();
		
		if(isFree(maze,x-1,y)) l.add(p(x-1,y));
		if(isFree(maze,x+1,y)) l.add(p(x+1,y));
		if(isFree(maze,x,y-1)) l.add(p(x,y-1));
		if(isFree(maze,x,y+1)) l.add(p(x,y+1));

		return l;
	}
	
	
	private int[] p(int x, int y)
	{return new int[]{x,y};}
	
	
	private boolean isFree(boolean[][] maze, int x, int y)
	{
		return 
			x>=0
			&& x<maze.length
			&& y>=0
			&& y<maze[0].length 
			&& maze[x][y];
	}
}
