package a.entity.gus06.data.perform.pick.strict01;

import a.framework.*;
import java.util.Collection;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170315";}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Map)		return pick((Map) obj);
		if(obj instanceof Collection)	return pick((Collection) obj);
		if(obj instanceof Object[])	return pick((Object[]) obj);
		if(obj instanceof File)		return pick((File) obj);
		
		if(obj instanceof double[])	return pick((double[]) obj);
		if(obj instanceof float[])	return pick((float[]) obj);
		if(obj instanceof long[])	return pick((long[]) obj);
		if(obj instanceof int[])	return pick((int[]) obj);
		if(obj instanceof boolean[])	return pick((boolean[]) obj);
		if(obj instanceof byte[])	return pick((byte[]) obj);
		if(obj instanceof short[])	return pick((short[]) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	
	private Object pick(Collection c) throws Exception
	{
		if(c.isEmpty()) return null;
		if(c.size()!=1) throw new Exception("Invalid size: "+c.size());
		return c.iterator().next();
	}
	
	private Object pick(Object[] o) throws Exception
	{
		if(o.length==0) return null;
		if(o.length!=1) throw new Exception("Invalid length: "+o.length);
		return o[0];
	}
	
	private Object pick(Map m) throws Exception
	{
		Object key = pick(m.keySet());
		return m.get(key);
	}
	
	private Object pick(File dir) throws Exception
	{
		File[] ff = dir.listFiles();
		if(ff==null || ff.length==0) return null;
		if(ff.length!=1)  throw new Exception("Invalid length: "+ff.length);
		return ff[0];
	}
	
	
	
	private Object pick(double[] o) throws Exception
	{
		if(o.length==0) return null;
		if(o.length!=1) throw new Exception("Invalid length: "+o.length);
		return Double.valueOf(o[0]);
	}
	
	private Object pick(float[] o) throws Exception
	{
		if(o.length==0) return null;
		if(o.length!=1) throw new Exception("Invalid length: "+o.length);
		return Float.valueOf(o[0]);
	}
	
	private Object pick(int[] o) throws Exception
	{
		if(o.length==0) return null;
		if(o.length!=1) throw new Exception("Invalid length: "+o.length);
		return Integer.valueOf(o[0]);
	}
	
	private Object pick(long[] o) throws Exception
	{
		if(o.length==0) return null;
		if(o.length!=1) throw new Exception("Invalid length: "+o.length);
		return Long.valueOf(o[0]);
	}
	
	private Object pick(boolean[] o) throws Exception
	{
		if(o.length==0) return null;
		if(o.length!=1) throw new Exception("Invalid length: "+o.length);
		return Boolean.valueOf(o[0]);
	}
	
	private Object pick(byte[] o) throws Exception
	{
		if(o.length==0) return null;
		if(o.length!=1) throw new Exception("Invalid length: "+o.length);
		return Byte.valueOf(o[0]);
	}
	
	private Object pick(short[] o) throws Exception
	{
		if(o.length==0) return null;
		if(o.length!=1) throw new Exception("Invalid length: "+o.length);
		return Short.valueOf(o[0]);
	}
}