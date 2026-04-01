package a.entity.gus06.sys.opposite1.list.sum;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160721";}


	private Service check;
	
	public EntityImpl() throws Exception
	{
		check = Outside.service(this,"gus06.sys.opposite1.check");
	}


	public Object t(Object obj) throws Exception
	{
		List[] ll = (List[]) obj;
		List list = new ArrayList();
		for(List l:ll)
		{
			if(check.f(l))
				removeFirst(list,l);
			else list.addAll(l);
		}
		return list;
	}
	
	private void removeFirst(List list1, List list2)
	{
		for(Object o:list2) list1.remove(o);
	}
}
