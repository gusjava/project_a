package a.entity.gus06.data.perform.apply;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.io.PrintStream;
import java.io.File;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20160710";}

	private Service wrap_pobj;
	private Service printStreamToP;

	public EntityImpl() throws Exception
	{
		wrap_pobj = Outside.service(this,"gus06.feature.wrap.po.e");
		printStreamToP = Outside.service(this,"gus06.convert.printstreamtop");
	}

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		return apply(o[0],o[1]);
	}
	
	private Object apply(Object o1, Object o2) throws Exception
	{
		if(o1==null) return null;
		
		if(o1 instanceof T) return ((T) o1).t(o2);
		if(o1 instanceof R) return ((R) o1).r((String) o2);
		if(o1 instanceof F) return Boolean.valueOf(((F) o1).f(o2));
		if(o1 instanceof H) return Double.valueOf(((H) o1).h(toDouble(o2)));
		if(o1 instanceof P) return wrapP((P) o1,o2);
		if(o1 instanceof PrintStream) return wrapP((P) printStreamToP.t(o1),o2);
		if(o1 instanceof File) return child((File) o1, (String) o2);
	
		if(o1 instanceof Map) return get((Map) o1,o2);
		if(o1 instanceof List) return get((List) o1,toInt(o2));
		if(o1 instanceof Set) return Boolean.valueOf(has((Set) o1,o2));
		
		if(o1 instanceof Object[]) return get((Object[]) o1,toInt(o2));
		if(o1 instanceof int[]) return get((int[]) o1,toInt(o2));
		if(o1 instanceof long[]) return get((long[]) o1,toInt(o2));
		if(o1 instanceof double[]) return get((double[]) o1,toInt(o2));
		if(o1 instanceof float[]) return get((float[]) o1,toInt(o2));
		if(o1 instanceof boolean[]) return get((boolean[]) o1,toInt(o2));
		
		throw new Exception("Invalid data type: "+o1.getClass().getName());
	}
	
	private double toDouble(Object obj)
	{return Double.parseDouble(""+obj);}
	
	private int toInt(Object obj)
	{return Integer.parseInt(""+obj);}
	
	private Object get(Map map, Object key)
	{return map.containsKey(key)?map.get(key):null;}
	
	private boolean has(Set set, Object value)
	{return set.contains(value);}
	
	private Object get(List list, int index)
	{
		int size = list.size();
		if(index<0) index = size+index;
		if(index<0 || index>=size) return null;
		return list.get(index);
	}
	
	private Object get(Object[] array, int index)
	{
		int size = array.length;
		if(index<0) index = size+index;
		if(index<0 || index>=size) return null;
		return array[index];
	}
	
	private Object get(int[] array, int index)
	{
		int size = array.length;
		if(index<0) index = size+index;
		if(index<0 || index>=size) return null;
		return array[index];
	}
	
	private Object get(long[] array, int index)
	{
		int size = array.length;
		if(index<0) index = size+index;
		if(index<0 || index>=size) return null;
		return array[index];
	}
	
	private Object get(double[] array, int index)
	{
		int size = array.length;
		if(index<0) index = size+index;
		if(index<0 || index>=size) return null;
		return array[index];
	}
	
	private Object get(float[] array, int index)
	{
		int size = array.length;
		if(index<0) index = size+index;
		if(index<0 || index>=size) return null;
		return array[index];
	}
	
	private Object get(boolean[] array, int index)
	{
		int size = array.length;
		if(index<0) index = size+index;
		if(index<0 || index>=size) return null;
		return array[index];
	}
	
	private E wrapP(P p, Object obj) throws Exception
	{return (E) wrap_pobj.t(new Object[]{p,obj});}
	
	private File child(File dir, String k)
	{return new File(dir,k);}
}
