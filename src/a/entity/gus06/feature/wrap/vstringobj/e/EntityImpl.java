package a.entity.gus06.feature.wrap.vstringobj.e;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160414";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		V v = (V) o[0];
		String key = (String) o[1];
		Object data = o[2];
		
		return new Wrap(v,key,data);
	}
	
	
	
	
	
	private class Wrap implements E
	{
		private V v;
		private String key;
		private Object data;
		
		public Wrap(V v, String key, Object data)
		{
			this.v = v;
			this.key = key;
			this.data = data;
		}
		
		public void e() throws Exception
		{
			v.v(key,data);
		}
	}
}
