package a.entity.gus06.convert.doublearray2toobjarray2;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180110";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		double[][] oo = (double[][]) obj;
		int nb1 = oo.length;
		int nb2 = nb1>0 ? oo[0].length : 0;
		
		Double[][] yy = new Double[nb1][nb2];
		for(int i=0;i<nb1;i++)
		for(int j=0;j<nb2;j++)
		yy[i][j] = Double.valueOf(oo[i][j]);
		
		return yy;
	}
}
