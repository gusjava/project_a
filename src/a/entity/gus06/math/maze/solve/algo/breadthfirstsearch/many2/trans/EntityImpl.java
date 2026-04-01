package a.entity.gus06.math.maze.solve.algo.breadthfirstsearch.many2.trans;

import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170923";}


	private Service bfs;

	public EntityImpl() throws Exception
	{
		bfs = Outside.service(this,"gus06.math.maze.solve.algo.breadthfirstsearch.many2");
	}


	public Object t(Object obj) throws Exception
	{
		Object[] t = (Object[]) obj;
		if(t.length!=3) throw new Exception("Wrong data number: "+t.length);
		
		boolean[][] maze = (boolean[][]) t[0];
		int[] start = (int[]) t[1];
		int[][] ends = findEnds(t[2]);
		
		maze = cloneMaze(maze);
		
		for(int i=0;i<ends.length;i++)
		maze[ends[i][0]][ends[i][1]] = true;
		
		
		bfs.v("maze",maze);
		bfs.v("start",start);
		bfs.v("ends",ends);
		
		bfs.e();
		
		return bfs.r("paths");
	}

	
	
	
	private int[][] findEnds(Object obj) throws Exception
	{
		if(obj instanceof int[][]) return (int[][]) obj;
		if(obj instanceof List) return findEnds_fromList((List) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	 
	
	private int[][] findEnds_fromList(List l)
	{
		int[][] t = new int[l.size()][2];
		for(int i=0;i<t.length;i++) t[i] = (int[]) l.get(i);
		return t;
	}
	
	
	private boolean[][] cloneMaze(boolean[][] maze0)
	{
		int size = maze0.length;
		boolean[][] maze1 = new boolean[size][size];
		for(int i=0;i<size;i++) for(int j=0;j<size;j++)
			maze1[i][j] = maze0[i][j];
		return maze1;
	}
}
