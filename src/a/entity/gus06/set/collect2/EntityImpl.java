package a.entity.gus06.set.collect2;

import a.framework.*;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20170326";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Set input = (Set) o[0];
		Set output = (Set) t(obj);
		
		input.clear();
		input.addAll(output);
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Set input = (Set) o[0];
		T t = (T) o[1];
		
		Set output = new HashSet();
		Iterator it = input.iterator();
		while(it.hasNext())
		{
			Object element = it.next();
			output.add(t.t(new Object[]{element,input}));
		}
		return output;
	}
}
