package a.entity.gus06.list.remove.strict;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20170419";}
	
	
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
		if(index==null) throw new Exception("Invalid index for list");
		
		list.remove(index.intValue());
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List list = (List) o[0];
		Integer index = (Integer) ruleToIndex.t(o);
		if(index==null) throw new Exception("Invalid index for list");
		
		List list1 = new ArrayList(list);
		list1.remove(index.intValue());
		return list1;
	}
}
