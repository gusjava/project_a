package a.entity.gus06.list.sortwith;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class EntityImpl implements Entity, T, P {

	public String creationDate() {return "20170501";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List input = (List) o[0];
		T trans = (T) o[1];
		
		Collections.sort(input,new Comparator1(trans));
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List input = (List) o[0];
		Comparator comparator = toComparator(o[1]);
		
		List output = new ArrayList(input);
		Collections.sort(output,comparator);
		return output;
	}
	
	
	
	private Comparator toComparator(Object obj) throws Exception
	{
		if(obj instanceof Comparator) return (Comparator) obj;
		if(obj instanceof T) return new Comparator1((T) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class Comparator1 implements Comparator
	{
		private T t;
		public Comparator1(T t) {this.t = t;}
		
		public int compare(Object o1, Object o2)
		{
			Integer n = (Integer) trans(t,new Object[]{o1,o2});
			return n.intValue();
		}
	}
	
	
	
	private Object trans(T t, Object o)
	{
		try{return t.t(o);}
		catch(Exception e)
		{Outside.err(this,"trans(T,Object)",e);}
		return null;
	}
}
