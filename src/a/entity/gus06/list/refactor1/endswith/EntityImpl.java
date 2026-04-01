package a.entity.gus06.list.refactor1.endswith;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T, P {

	public String creationDate() {return "20160713";}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List list = (List) o[0];
		F filter = (F) o[1];
		
		List newList = refactor(list,filter);
		
		list.clear();
		list.addAll(newList);
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List list = (List) o[0];
		F filter = (F) o[1];
		
		return refactor(list,filter);
	}
	
	
	
	private List refactor(List list, F filter) throws Exception
	{
		List list1 = new ArrayList();
		List current = new ArrayList();
		
		for(int i=0;i<list.size();i++)
		{
			Object elem = list.get(i);
			current.add(elem);
			if(filter.f(elem))
			{
				if(!current.isEmpty()) list1.add(current);
				current = new ArrayList();
			}
		}
		
		if(!current.isEmpty()) list1.add(current);
		return list1;
	}
}
