package a.entity.gus06.data.perform.pop.at;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220621";}


	private Service ruleToIndex;
	
	public EntityImpl() throws Exception
	{
		ruleToIndex = Outside.service(this,"gus06.list.ruletoindex");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		Object key = o[1];
		
		if(input instanceof List) return pop((List) input, key);
		
		throw new Exception("Invalid data type: "+input.getClass().getName());
	}
	
	
	
	private Object pop(List list, Object key) throws Exception
	{
		Integer index = (Integer) ruleToIndex.t(new Object[]{list,key});
		if(index==null) return null;
		return list.remove(index.intValue());
	}
}