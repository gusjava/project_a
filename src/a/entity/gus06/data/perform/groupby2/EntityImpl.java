package a.entity.gus06.data.perform.groupby2;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180310";}


	private Service performList;
	private Service performSet;
	private Service findList;
	
	
	public EntityImpl() throws Exception
	{
		performList = Outside.service(this,"gus06.list.groupby2");
		performSet = Outside.service(this,"gus06.set.groupby2");
		findList = Outside.service(this,"gus06.find.list");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		T t = (T) o[1];
		
		if(value instanceof List) return performList.t(new Object[]{value,t});
		if(value instanceof Set) return performSet.t(new Object[]{value,t});
		
		if(value instanceof Object[]) return performList.t(new Object[]{findList.t(value),t});
		if(value instanceof int[]) return performList.t(new Object[]{findList.t(value),t});
		if(value instanceof short[]) return performList.t(new Object[]{findList.t(value),t});
		if(value instanceof long[]) return performList.t(new Object[]{findList.t(value),t});
		if(value instanceof double[]) return performList.t(new Object[]{findList.t(value),t});
		if(value instanceof float[]) return performList.t(new Object[]{findList.t(value),t});
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
}
