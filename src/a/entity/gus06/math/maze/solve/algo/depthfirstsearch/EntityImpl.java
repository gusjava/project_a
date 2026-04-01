package a.entity.gus06.math.maze.solve.algo.depthfirstsearch;

import a.framework.*;

public class EntityImpl extends AbstractSearch implements Entity {

	public String creationDate() {return "20150309";}

	private int[][] path0;
	private int index;
	
	protected void performSearch()
	{
		init();
		search();
	}
	
	
	private void init()
	{
		path0 = new int[(int)(maze_x*maze_y*0.5)][2];
		index = 0;
		path0[index] = start;
		current = start;
		fill(start);
	}
	
	
	private void search()
	{
		int[] move = findNextMove();
		if(move!=null)  // forward
		{
			increaseCurrent(move);
			if(isEnd(move)) {resolvePath();return;}
		}
		else // backward
		{
			decreaseCurrent();
			if(index==-1) return;
		}
		
		search();
	}
	
	
	private void decreaseCurrent()
	{
		index--;
		current = index>=0?path0[index]:null;
		moved();
	}
	
	
	private void increaseCurrent(int[] p)
	{
		index++;
		path0[index] = p;
		current = p;
		fill(p);
		moved();
	}
	
	
	private void resolvePath()
	{
		path = new int[index+1][2];
		for(int i=0;i<index+1;i++) path[i] = path0[i];
	}
	
	
	private int[] findNextMove()
	{
		int d_min = d_max+1;
		int[] p_min = null;
		
		for(int[] p:freeNeighbours(current))
		{
			int d = distanceToEnd(p);
			if(d<d_min) {d_min = d;p_min = p;}
		}
		return p_min;
	}
}