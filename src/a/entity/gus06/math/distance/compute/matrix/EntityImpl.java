package a.entity.gus06.math.distance.compute.matrix;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170106";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List l = (List) o[0];
		T t = (T) o[1];
		
		int number = l.size();
		if(number==0) return new double[0][0];
		if(number==1) return new double[][]{{0}};
		
		double[][] matrix = new double[number][number];
		
		for(int i=0;i<number;i++) matrix[i][i] = 0;
		
		for(int i=1;i<number;i++)
		for(int j=i+1;j<number;j++)
		{
			Object aa = l.get(i);
			Object bb = l.get(j);
			
			double d = ((Double) t.t(new Object[]{aa,bb})).doubleValue();
			matrix[i][j] = d;
			matrix[j][i] = d;
		}
		
		return matrix;
	}
}
