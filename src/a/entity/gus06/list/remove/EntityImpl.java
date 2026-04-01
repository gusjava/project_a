package a.entity.gus06.list.remove;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20161215";}
	
	
	private Service ruleToIndex;
	
	public EntityImpl() throws Exception
	{
		ruleToIndex = Outside.service(this,"gus06.list.ruletoindex");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List list = (List) o[0];
		Integer index = (Integer) ruleToIndex.t(o);
		if(index==null) return;
		list.remove(index.intValue());
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List list = (List) o[0];
		Integer index = (Integer) ruleToIndex.t(o);
		
		List list1 = new ArrayList(list);
		if(index==null) return list1;
		
		list1.remove(index.intValue());
		return list1;
	}
}
