package a.entity.gus06.feature.wrap.vstring.p;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160414";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		V v = (V) o[0];
		String key = (String) o[1];
		
		return new Wrap(v,key);
	}
	
	
	
	
	
	private class Wrap implements P
	{
		private V v;
		private String key;
		
		public Wrap(V v, String key)
		{
			this.v = v;
			this.key = key;
		}
		
		public void p(Object obj) throws Exception
		{
			v.v(key,obj);
		}
	}
}
