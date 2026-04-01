package a.entity.gus06.math.statistics.regression.linear.method1;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200113";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		double[] x = (double[]) o[0];
		double[] y = (double[]) o[1];
		
		if(x.length!=y.length) throw new Exception("Same length is required: x.length="+x.length+" y.length="+y.length);
		
		int nb = x.length;
		double sum_x = 0;
		double sum_y = 0;
		double sum_xx = 0;
		double sum_xy = 0;
		double sum_yy = 0;
	
		for(int i=0;i<nb;i++)
		{
			sum_x += x[i];
			sum_y += y[i];
			sum_xy += x[i]*y[i];
			sum_xx += x[i]*x[i];
			sum_yy += y[i]*y[i];
		}
		
		double slope = (nb*sum_xy-sum_x*sum_y) / (nb*sum_xx-sum_x*sum_x);
		double intercept = (sum_y-slope*sum_x) / nb;
	
		return new double[]{slope,intercept};
	}
}