package a.entity.gus06.math.matrixdouble.op.soustraction;

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
		
		if(x1!=x2)  throw new Exception("Different row number for matrix: "+x1+" & "+x2);
		if(y1!=y2)  throw new Exception("Different column number for matrix: "+y1+" & "+y2);
		
		double[][] result = new double[x1][y1];
		
		for(int i=0;i<x1;i++)
		for(int j=0;j<y1;j++)
		result[i][j] = d1[i][j] - d2[i][j];
		
		return result;
	}
}
