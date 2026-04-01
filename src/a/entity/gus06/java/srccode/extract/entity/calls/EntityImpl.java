package a.entity.gus06.java.srccode.extract.entity.calls;

import a.framework.*;
import java.util.ArrayList;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150311";}


	private Service callResources;
	private Service callServices;


	public EntityImpl() throws Exception
	{
		callResources = Outside.service(this,"gus06.java.srccode.extract.entity.calls.resource");
		callServices = Outside.service(this,"gus06.java.srccode.extract.entity.calls.service");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		List list1 = (List) callResources.t(obj);
		List list2 = (List) callServices.t(obj);
		
		List list = new ArrayList();
		list.addAll(list1);
		list.addAll(list2);
		
		return list;
	}
}
