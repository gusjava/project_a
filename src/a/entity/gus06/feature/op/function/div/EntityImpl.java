package a.entity.gus06.feature.op.function.div;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160802";}

	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof H[])
		{
			H[] hh = (H[]) obj;
			if(hh.length!=2) throw new Exception("Wrong data number: "+hh.length);
			
			return new H1(hh[0],hh[1]);
		}
		if(obj instanceof Object[])
		{
			Object[] oo = (Object[]) obj;
			if(oo.length!=2) throw new Exception("Wrong data number: "+oo.length);
			
			if(!(oo[0] instanceof H) && !(oo[1] instanceof H))
				throw new Exception("No function found in data array");
				
			return new H2(oo[0],oo[1]);
		}
		throw new Exception("Invalid data type: "+obj.getClass());
	}
	
	
	
	private class H1 implements H
	{
		private H h1;
		private H h2;
		
		public H1(H h1, H h2)
		{
			this.h1 = h1;
			this.h2 = h2;
		}
		
		public double h(double v) throws Exception
		{
			double v1 = h1.h(v);
			double v2 = h2.h(v);
			return v1/v2;
		}
	}
	
	
	
	private class H2 implements H
	{
		private Object o1;
		private Object o2;
		
		public H2(Object o1, Object o2)
		{
			this.o1 = o1;
			this.o2 = o2;
		}
		
		public double h(double v) throws Exception
		{
			double v1 = find(o1,v);
			double v2 = find(o2,v);
			return v1/v2;
		}
	}
	
	
	private double find(Object o, double v) throws Exception
	{
		if(o instanceof Number) return ((Number)o).doubleValue();
		if(o instanceof H) return ((H)o).h(v);
		
		throw new Exception("Invalid data type: "+o.getClass().getName());
	}
}
