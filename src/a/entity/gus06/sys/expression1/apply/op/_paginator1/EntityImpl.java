package a.entity.gus06.sys.expression1.apply.op._paginator1;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161219";}


	private Service performList;
	private Service performArray;
	
	public EntityImpl() throws Exception
	{
		performList = Outside.service(this,"gus06.list.paginator.holder");
		performArray = Outside.service(this,"gus06.array.paginator.holder");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof List) return performList.t(obj);
		if(obj instanceof Object[]) return performArray.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
