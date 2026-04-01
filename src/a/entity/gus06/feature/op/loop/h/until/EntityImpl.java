package a.entity.gus06.feature.op.loop.h.until;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160309";}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		H h = (H) o[0];
		F f = (F) o[1];
		
		return new H1(h,f);
	}
	
	private int toInt(Object obj) throws Exception
	{
		if(obj instanceof Integer) return ((Integer)obj).intValue();
		if(obj instanceof String) return Integer.parseInt((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class H1 implements H
	{
		private H h;
		private F f;
		
		public H1(H h, F f)
		{
			this.h = h;
			this.f = f;
		}
		
		public double h(double v) throws Exception
		{
			double r = v;
			while(!f.f(Double.valueOf(v))) r = h.h(r);
			return r;
		}
	}
}
