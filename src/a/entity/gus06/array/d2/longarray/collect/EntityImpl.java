package a.entity.gus06.array.d2.longarray.collect;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20180114";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		long[][] input = (long[][]) o[0];
		double[][] output = (double[][]) t(obj);
		
		int nb1 = input.length;
		int nb2 = nb1>0 ? input[0].length : 0;
		
		for(int i=0;i<nb1;i++)
		for(int j=0;j<nb2;j++)
		{
			input[i][j] = (long) output[i][j];
		}
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		long[][] input = (long[][]) o[0];
		H h = (H) o[1];
		
		int nb1 = input.length;
		int nb2 = nb1>0 ? input[0].length : 0;
		
		double[][] output = new double[nb1][nb2];
		
		for(int i=0;i<nb1;i++)
		for(int j=0;j<nb2;j++)
		{
			double value = (double) input[i][j];
			output[i][j] = h.h(value);
		}
		return output;
	}
}
