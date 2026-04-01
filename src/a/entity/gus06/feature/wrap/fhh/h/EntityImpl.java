package a.entity.gus06.feature.wrap.fhh.h;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160819";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		F f = (F) o[0];
		H h1 = (H) o[1];
		H h2 = (H) o[2];
		
		return new Wrap(f,h1,h2);
	}
	
	
	
	
	
	private class Wrap implements H
	{
		private F f;
		private H h1;
		private H h2;
		
		public Wrap(F f, H h1, H h2)
		{
			this.f = f;
			this.h1 = h1;
			this.h2 = h2;
		}
		
		public double h(double value) throws Exception
		{
			H h = f.f(Double.valueOf(value)) ? h1 : h2;
			return h!=null ? h.h(value) : 0;
		}
	}
}
