package a.entity.gus06.sys.parser3.resolver1.op.seq.sum.motley.list;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160802";}



	public Object t(Object obj) throws Exception
	{
		Object[] oo = (Object[]) obj;
		
		List sum = new ArrayList();
		for(Object o:oo) sum.addAll(toList(o));
		return sum;
	}
	
	private List toList(Object o)
	{
		if(o instanceof List) return (List) o;
		List l = new ArrayList();
		l.add(o);
		return l;
	}
}
