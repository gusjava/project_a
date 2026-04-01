package a.entity.gus06.data.perform.average.of;

import a.framework.*;
import java.util.List;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160820";}


	
	private Service performList;
	private Service findList;
	
	
	public EntityImpl() throws Exception
	{
		performList = Outside.service(this,"gus06.list.avg.of");
		findList = Outside.service(this,"gus06.find.list");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		T t = (T) o[1];
		
		if(input instanceof List)
			return performList.t(new Object[]{input,t});
			
		if(input instanceof Set)
			return performList.t(new Object[]{findList.t(input),t});
		
		if(input instanceof Object[])
			return performList.t(new Object[]{findList.t(input),t});
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
