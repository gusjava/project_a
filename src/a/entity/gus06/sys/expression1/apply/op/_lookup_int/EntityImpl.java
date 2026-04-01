package a.entity.gus06.sys.expression1.apply.op._lookup_int;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180412";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof H) return new T1((H) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private H h;
		public T1(H h) {this.h = h;}
		
		public Object t(Object obj) throws Exception
		{
			int len = ((Integer) obj).intValue();
			return buildLookup(h,len);
		}
	}
	
	
	private int[] buildLookup(H h, int len) throws Exception
	{
		int[] lookup = new int[len];
		for(int i=0;i<len;i++) lookup[i] = (int) h.h((double) i);
		return lookup;
	}
}
