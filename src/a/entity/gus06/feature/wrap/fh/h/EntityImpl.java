package a.entity.gus06.feature.wrap.fh.h;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160819";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		F f = (F) o[0];
		H h = (H) o[1];
		
		return new Wrap(f,h);
	}
	
	
	
	
	
	private class Wrap implements H
	{
		private F f;
		private H h;
		
		public Wrap(F f, H h)
		{
			this.f = f;
			this.h = h;
		}
		
		public double h(double value) throws Exception
		{
			return f.f(Double.valueOf(value)) ? h.h(value) : value;
		}
	}
}
