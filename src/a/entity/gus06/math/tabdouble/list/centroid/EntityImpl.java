package a.entity.gus06.math.tabdouble.list.centroid;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151230";}



	public Object t(Object obj) throws Exception
	{
		List l = (List) obj;
		
		int number = l.size();
		if(number==0) return null;
		
		double[] el0 = (double[]) l.get(0);
		int dim = el0.length;
		
		double[] r = new double[dim];
		for(int i=0;i<dim;i++) r[i] = 0;
		
		for(int i=0;i<number;i++)
		{
			double[] el = (double[]) l.get(i);
			if(el.length!=dim) throw new Exception("Invalid dimension for tab at index "+i+": "+el.length);
			
			for(int j=0;j<dim;j++) r[j] += el[j];
		}
		
		for(int i=0;i<dim;i++) r[i] /= number;
		
		return r;
	}
}
