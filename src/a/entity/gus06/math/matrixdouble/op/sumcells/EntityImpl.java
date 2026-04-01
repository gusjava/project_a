package a.entity.gus06.math.matrixdouble.op.sumcells;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180110";}

	
	public Object t(Object obj) throws Exception
	{
		double[][] dd = (double[][]) obj;
		
		int nb1 = dd.length;
		int nb2 = nb1>0 ? dd[0].length : 0;
		
		double sum = 0;
		
		for(int i=0;i<nb1;i++)
		for(int j=0;j<nb2;j++)
		sum += dd[i][j];
		
		return Double.valueOf(sum);
	}
}
