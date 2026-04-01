package a.entity.gus06.list.add.y;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20160816";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List list = (List) o[0];
		Object data = o[1];
		
		if(list.isEmpty() || !list.get(list.size()-1).equals(data))
		list.add(data);
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List list = (List) o[0];
		Object data = o[1];
		
		List list1 = new ArrayList(list);
		
		if(list.isEmpty() || !list.get(list.size()-1).equals(data))
		list1.add(data);
		
		return list1;
	}
}
