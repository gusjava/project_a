package a.entity.gus06.feature.wrap.hh.h;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161210";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		H h1 = (H) o[0];
		H h2 = (H) o[1];
		
		return new Wrap(h1,h2);
	}
	
	
	
	
	
	private class Wrap implements H
	{
		private H h1;
		private H h2;
		
		public Wrap(H h1, H h2)
		{
			this.h1 = h1;
			this.h2 = h2;
		}
		
		public double h(double value) throws Exception
		{
			value = h1.h(value);
			return h2.h(value);
		}
	}
}
