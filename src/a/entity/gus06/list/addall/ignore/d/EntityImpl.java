package a.entity.gus06.list.addall.ignore.d;

import a.framework.*;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20161218";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List list = (List) o[0];
		Collection data = (Collection) o[1];
		
		Iterator it = data.iterator();
		while(it.hasNext())
		{
			Object elem = it.next();
			if(!list.contains(elem)) list.add(elem);
			else System.out.println("data ignored: "+elem);
		}
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List list = (List) o[0];
		Collection data = (Collection) o[1];
		
		List list1 = new ArrayList(list);
		
		Iterator it = data.iterator();
		while(it.hasNext())
		{
			Object elem = it.next();
			if(!list.contains(elem)) list1.add(elem);
			else System.out.println("data ignored: "+elem);
		}
		
		return list1;
	}
}
