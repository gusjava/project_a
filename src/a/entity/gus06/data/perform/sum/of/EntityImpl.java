package a.entity.gus06.data.perform.sum.of;

import a.framework.*;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160820";}


	
	private Service performArray;
	private Service performList;
	
	
	public EntityImpl() throws Exception
	{
		performArray = Outside.service(this,"gus06.array.objectarray.sum.of");
		performList = Outside.service(this,"gus06.list.sum.of");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		T t = (T) o[1];
		
		if(input instanceof Object[])
			return performArray.t(new Object[]{input,t});
			
		if(input instanceof List)
			return performList.t(new Object[]{input,t});
			
		if(input instanceof Set)
			return performList.t(new Object[]{new ArrayList((Set) input),t});
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
