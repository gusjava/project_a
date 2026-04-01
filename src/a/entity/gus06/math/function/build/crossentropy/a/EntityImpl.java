package a.entity.gus06.math.function.build.crossentropy.a;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20171015";}
	

	
	public Object t(Object obj) throws Exception
	{
		double a = ((Double) obj).doubleValue();
		return new H1(a);
	}
	
	
	
	private class H1 implements H
	{
		private double log_a;
		private double log_a1;
		
		public H1(double a)
		{
			log_a = Math.log(a);
			log_a1 = Math.log(1-a);
		}
		
		public double h(double value)
		{return -1*(value*log_a + (1-value)*log_a1);}
	}
}
