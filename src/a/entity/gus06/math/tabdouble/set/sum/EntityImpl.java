package a.entity.gus06.math.tabdouble.set.sum;

import a.framework.*;
import java.util.Set;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180503";}



	public Object t(Object obj) throws Exception
	{
		Set l = (Set) obj;
		
		int number = l.size();
		if(number==0) return null;
		
		double[] el0 = (double[]) l.iterator().next();
		int dim = el0.length;
		
		double[] r = new double[dim];
		for(int i=0;i<dim;i++) r[i] = 0;
		
		Iterator it = l.iterator();
		while(it.hasNext())
		{
			double[] el = (double[]) it.next();
			if(el.length!=dim) throw new Exception("Invalid dimension for tab: "+el.length);
			
			for(int i=0;i<dim;i++) r[i] += el[i];
		}
		
		return r;
	}
}
