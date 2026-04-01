package a.entity.gus06.list.sortwith.inv;

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
		T trans = (T) o[1];
		
		List output = new ArrayList(input);
		Collections.sort(output,new Comparator1(trans));
		return output;
	}
	
	
	private class Comparator1 implements Comparator
	{
		private T t;
		public Comparator1(T t) {this.t = t;}
		
		public int compare(Object o1, Object o2)
		{
			Integer n = (Integer) trans(t,new Object[]{o1,o2});
			return -1*n.intValue();
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
