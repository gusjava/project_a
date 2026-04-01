package a.entity.gus06.math.matrixint.op.sum;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170120";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] oo = (Object[]) obj;
		int nb = oo.length;
		
		if(nb==0) return null;
		
		int x = -1;
		int y = -1;
		
		for(int a=0;a<oo.length;a++)
		{
			int[][] d = (int[][]) oo[a];
			
			int x1 = d.length;
			int y1 = d[0].length;
			
			if(x==-1) x = x1;
			else if(x!=x1) throw new Exception("Invalid row number for matrix at index "+a+": "+x1);
			
			if(y==-1) y = y1;
			else if(y!=y1) throw new Exception("Invalid column number for matrix at index "+a+": "+y1);
		}
		
		int[][] result = new int[x][y];
		
		for(int i=0;i<x;i++)
		for(int j=0;j<y;j++)
		result[i][j] = 0;
		
		for(int a=0;a<oo.length;a++)
		{
			int[][] d = (int[][]) oo[a];
			
			for(int i=0;i<x;i++)
			for(int j=0;j<y;j++)
			result[i][j] += d[i][j];
		}
		
		return result;
	}
}
