package a.entity.gus06.list.addall0.strict;

import a.framework.*;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20161207";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List list = (List) o[0];
		Collection data = (Collection) o[1];
		
		check(list,data);
		
		list.addAll(0,data);
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List list = (List) o[0];
		Collection data = (Collection) o[1];
		
		check(list,data);
		
		List list1 = new ArrayList(list);
		list1.addAll(0,data);
		
		return list1;
	}
	
	
	
	private void check(List list, Collection data) throws Exception
	{
		Iterator it = data.iterator();
		while(it.hasNext())
		{
			Object elem = it.next();
			if(list.contains(elem))
			throw new Exception("Element already used inside list: "+elem);
		}
	}
}
