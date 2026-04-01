package a.entity.gus06.list.clean;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20170201";}
	
	
	public void p(Object obj) throws Exception
	{
		List list = (List) obj;
		List newList = clean(list);
		list.clear();
		list.addAll(newList);
	}
	
	
	public Object t(Object obj) throws Exception
	{
		List list = (List) obj;
		return clean(list);
	}
	
	
	private List clean(List list)
	{
		List list1 = new ArrayList();
		for(int i=0;i<list.size();i++)
		{
			Object element = cleanElement(list.get(i));
			if(element!=null) list1.add(element);
		}
		return list1;
	}
	
	
	
	private Object cleanElement(Object element)
	{
		if(element==null) return null;
		if(element instanceof String)
		{
			String s = ((String) element).trim();
			return s.equals("")?null:s;
		}
		if(element instanceof Collection)
		{
			Collection c = (Collection) element;
			return c.isEmpty()?null:c;
		}
		if(element instanceof Map)
		{
			Map m = (Map) element;
			return m.isEmpty()?null:m;
		}
		return element;
	}
}
