package a.entity.gus06.convert.numbertoh;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160802";}


	public Object t(Object obj) throws Exception
	{return new H1((Number) obj);}
	
	
	
	private class H1 implements H
	{
		private double v1;
		
		public H1(Number n)
		{v1 = n.doubleValue();}
		
		public double h(double v) throws Exception
		{return v1;}
	}
}
