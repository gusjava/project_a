package a.entity.gus06.math.matrixdouble.op.multiplication;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150309";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		double[][] d1 = (double[][]) o[0];
		double[][] d2 = (double[][]) o[1];
		
		int x1 = d1.length;
		int y1 = d1[0].length;
		
		int x2 = d2.length;
		int y2 = d2[0].length;
		
		if(y1!=x2)  throw new Exception("Improper matrix size for multiplication: ["+x1+","+y1+"] & ["+x2+","+y2+"]");
		
		double[][] result = new double[x1][y2];
		
		for(int i=0;i<x1;i++)
		for(int j=0;j<y2;j++)
		{
			result[i][j] = 0;
			for(int k=0;k<y1;k++)
			result[i][j] += d1[i][k] * d2[k][j];
		}
		return result;
	}
}
