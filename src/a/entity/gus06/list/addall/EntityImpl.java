package a.entity.gus06.list.addall;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20160128";}


	private Service findList;
	
	public EntityImpl() throws Exception
	{
		findList = Outside.service(this,"gus06.find.list");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List list = (List) o[0];
		List data = (List) findList.t(o[1]);
		
		list.addAll(data);
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List list = (List) o[0];
		List data = (List) findList.t(o[1]);
		
		List list1 = new ArrayList(list);
		list1.addAll(data);
		
		return list1;
	}
}
