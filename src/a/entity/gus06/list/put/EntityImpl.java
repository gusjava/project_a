package a.entity.gus06.list.put;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20170428";}


	private Service ruleToIndex;
	
	public EntityImpl() throws Exception
	{
		ruleToIndex = Outside.service(this,"gus06.list.ruletoindex1");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		List list = (List) o[0];
		Object key = o[1];
		Object value = o[2];
		
		Integer n = (Integer) ruleToIndex.t(new Object[]{list,key});
		if(n==null) throw new Exception("Invalid rule for index: "+key);
		
		int index = n.intValue();
		while(index>=list.size()) list.add(null);
		
		list.set(index,value);
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		List list = (List) o[0];
		Object key = o[1];
		Object value = o[2];
		
		List list1 = new ArrayList(list);
		
		Integer n = (Integer) ruleToIndex.t(new Object[]{list,key});
		if(n==null) throw new Exception("Invalid rule for index: "+key);
		
		int index = n.intValue();
		while(index>=list.size()) list1.add(null);
		
		list1.set(index,value);
		
		return list1;
	}
}
