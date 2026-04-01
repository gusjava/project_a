package a.entity.gus06.appli.gameoflife.transition;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150323";}


	public Object t(Object obj) throws Exception
	{
		boolean[][] b = (boolean[][]) obj;
		
		int x = b.length;
		if(x==0) throw new Exception("Empty size for data");
		
		int y = b[0].length;
		if(y==0) throw new Exception("Empty size for data");
		
		boolean[][] b1 = new boolean[x][y];
		for(int i=0;i<x;i++)
		for(int j=0;j<y;j++)
		b1[i][j] = findNextValue(b,i,j);
		
		return b1;
	}

	private boolean findNextValue(boolean[][] b, int i, int j)
	{
		int n = aliveNeighbours(b,i,j);
		if(b[i][j]) return n==2 || n==3;
		return n==3;
	}
	
	private int aliveNeighbours(boolean[][] b, int i, int j)
	{
		int x = b.length;
		int y = b[0].length;
		
		int n = 0;
		
		if(i>0 && j>0 && b[i-1][j-1]) n++;
		if(i>0 && b[i-1][j]) n++;
		if(i>0 && j<y-1 && b[i-1][j+1]) n++;
		
		if(j>0 && b[i][j-1]) n++;
		if(j<y-1 && b[i][j+1]) n++;
		
		if(i<x-1 && j>0 && b[i+1][j-1]) n++;
		if(i<x-1 && b[i+1][j]) n++;
		if(i<x-1 && j<y-1 && b[i+1][j+1]) n++;
		
		return n;
	}
}