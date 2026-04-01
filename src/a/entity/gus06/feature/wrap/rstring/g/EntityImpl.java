package a.entity.gus06.feature.wrap.rstring.g;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160225";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		R r = (R) o[0];
		String key = (String) o[1];
		
		return new Wrap(r,key);
	}
	
	
	
	
	
	private class Wrap implements G
	{
		private R r;
		private String key;
		
		public Wrap(R r, String key)
		{
			this.r = r;
			this.key = key;
		}
		
		public Object g() throws Exception
		{
			return r.r(key);
		}
	}
}
