package a.entity.gus06.list.insert;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20160131";}

	
	private Service ruleToIndex;
	
	public EntityImpl() throws Exception
	{
		ruleToIndex = Outside.service(this,"gus06.list.ruletoindex");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		List list = (List) o[0];
		Object index = o[1];
		Object data = o[2];
		
		Integer index1 = (Integer) ruleToIndex.t(new Object[]{list,index});
		list.add(index1.intValue(),data);
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		List list = (List) o[0];
		Object index = o[1];
		Object data = o[2];
		
		List newList = new ArrayList(list);
		Integer index1 = (Integer) ruleToIndex.t(new Object[]{list,index});
		newList.add(index1.intValue(),data);
		return newList;
	}
}