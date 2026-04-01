package a.entity.gus06.math.matrixdouble.op.transposition;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150309";}
	
	
	public Object t(Object obj) throws Exception
	{
		double[][] d = (double[][]) obj;
		
		int x = d.length;
		int y = d[0].length;
		
		double[][] result = new double[y][x];
		
		for(int i=0;i<x;i++)
		for(int j=0;j<y;j++)
		result[j][i] = d[i][j];
		
		return result;
	}
}
