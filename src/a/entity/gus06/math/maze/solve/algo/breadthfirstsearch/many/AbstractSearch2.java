package a.entity.gus06.math.maze.solve.algo.breadthfirstsearch.many;

import java.util.ArrayList;
import java.util.List;
import a.framework.*;


public abstract class AbstractSearch2 extends S1 implements V, R, Runnable {


    
    private boolean[][] state;
    
    protected int[] start;
    protected int[] current;
    protected int[][] ends;
    
    protected int maze_x;
    protected int maze_y;
    protected int d_max;
    
    protected int[][] path;
    
    
    
    
    
    public void run()
    {
    	path = null;
    	current = null;
    	
    	if(hasFreeEnd())
    		performSearch();
    	searchOver();
    }
    
    protected abstract void performSearch();
    
    
    
    
    
    private boolean hasFreeEnd()
    {
    	for(int[] e:ends)
    		if(isFree(e)) return true;
    	return false;
    }
    
    
    
    protected boolean isEnd(int[] p)
    {
    	for(int[] e:ends)
    		if(equals(e,p)) return true;
    	return false;
    }
    
    
    
    
    

    public void v(String key, Object obj) throws Exception
    {
    	if(key.equals("maze"))
    	{
    		initMaze((boolean[][]) obj);
    		return;
    	}
    	if(key.equals("start"))
    	{
    		start = (int[]) obj;
    		if(start.length!=2) throw new Exception("Invalid size for start int[]: "+start.length);
    		return;
    	}
    	if(key.equals("ends"))
    	{
    		ends = (int[][]) obj;
    		return;
    	}
        throw new Exception("Unknown key: "+key);
    }
    
    
    
    
    
    
    
    
    public Object r(String key) throws Exception
    {
    	if(key.equals("path")) return path;
    	if(key.equals("current")) return current;
    	if(key.equals("state")) return state;
    	
    	if(key.equals("keys")) return new String[]{"path","current","state"};
        throw new Exception("Unknown key: "+key);
    }
    
    
    

    
    
    
    
    protected List<int[]> freeNeighbours(int[] p)
    {
    	int x = p[0];
    	int y = p[1];
    	
    	List<int[]> l = new ArrayList<>();
    	
    	if(isFree(x-1,y)) l.add(p(x-1,y));
    	if(isFree(x+1,y)) l.add(p(x+1,y));
    	if(isFree(x,y-1)) l.add(p(x,y-1));
    	if(isFree(x,y+1)) l.add(p(x,y+1));

    	return l;
    }
    
    
    
    
    private void initMaze(boolean[][] m) throws Exception
    {
    	if(m.length==0)  throw new Exception("maze_x value is 0");
    	if(m[0].length==0)  throw new Exception("maze_y value is 0");
		
    	maze_x = m.length;
		maze_y = m[0].length;
    	d_max = maze_x + maze_y;
    	
    	state = new boolean[maze_x][maze_y];
    	for(int i=0;i<maze_x;i++) for(int j=0;j<maze_y;j++)
    		state[i][j] = m[i][j];
    }
    
    
    
    
    
    protected void moved()
    {send(this,"moved()");}
    
    protected void searchOver()
    {send(this,"searchOver()");}
    
    
    
    
    
    
    
    

    protected void fill(int[] p)
    {fill(p[0],p[1]);}
    
    
    protected void fill(int x, int y)
    {if(isValid(x,y)) state[x][y] = false;}
    
    
    protected int[] p(int x, int y)
    {return new int[]{x,y};}
    
    
    protected boolean isFree(int[] p)
    {return p!=null && isFree(p[0],p[1]);}
    
    
    protected boolean isFree(int x, int y)
    {return isValid(x,y) && state[x][y];}

    
    protected boolean isValid(int x, int y)
    {return x>=0 && x<maze_x && y>=0 && y<maze_y;}
    
    
    protected int distanceTo(int[] p1, int[] p2)
    {return Math.abs(p1[0]-p2[0]) + Math.abs(p1[1]-p2[1]);}
    
    
    protected boolean equals(int[] p1, int[] p2)
    {return p1!=null && p2!=null && p1[0]==p2[0] && p1[1]==p2[1];}
    
    
    protected String toString(int[] p)
    {return p[0]+" "+p[1];}
}

